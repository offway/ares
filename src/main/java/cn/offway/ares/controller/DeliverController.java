package cn.offway.ares.controller;

import cn.offway.ares.domain.PhAddress;
import cn.offway.ares.domain.PhOrderGoods;
import cn.offway.ares.domain.PhOrderInfo;
import cn.offway.ares.service.*;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发货
 *
 * @author wn
 */
@Controller
public class DeliverController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PhOrderExpressInfoService phOrderExpressInfoService;
    @Autowired
    private PhOrderGoodsService phOrderGoodsService;
    @Autowired
    private PhOrderInfoService phOrderInfoService;
    @Autowired
    private PhBrandService brandService;
    @Autowired
    private PhAddressService addressService;

    @RequestMapping("/deliver.html")
    public String deliver(ModelMap map) {
        map.addAttribute("brands", brandService.findAll());
        return "deliver";
    }

    /**
     * 查询数据
     */
    @ResponseBody
    @RequestMapping("/deliver-data")
    public Map<String, Object> deliverData(HttpServletRequest request, String orderNo, String realName, String position, String unionid, Long brandId, String isOffway) {
        String sortCol = request.getParameter("iSortCol_0");
        String sortName = request.getParameter("mDataProp_" + sortCol);
        String sortDir = request.getParameter("sSortDir_0");
        int sEcho = Integer.parseInt(request.getParameter("sEcho"));
        int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
        int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
        PageRequest pr = new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName);
        Page<PhOrderInfo> pages = phOrderInfoService.findByPage(realName.trim(), position.trim(), orderNo.trim(), null != unionid ? unionid.trim() : unionid, brandId, isOffway, pr);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> list = new ArrayList<>();
        for (PhOrderInfo info : pages.getContent()) {
            Map<String, Object> m = mapper.convertValue(info, Map.class);
            PhAddress address = addressService.findOne(info.getAddressId());
            if (address != null) {
                m.put("toRealName", address.getRealName());
                m.put("toPhone", address.getPhone());
                m.put("toContent", address.getContent());
            } else {
                m.put("toRealName", "");
                m.put("toPhone", "");
                m.put("toContent", "");
            }
            list.add(m);
        }
        // 为操作次数加1，必须这样做
        int initEcho = sEcho + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("sEcho", initEcho);
        map.put("iTotalRecords", pages.getTotalElements());//数据总条数  
        map.put("iTotalDisplayRecords", pages.getTotalElements());//显示的条数  
        map.put("aData", list);//数据集合
        return map;
    }

    @ResponseBody
    @RequestMapping("/deliver-goods")
    public List<PhOrderGoods> phOrderGoods(String orderNo) {
        return phOrderGoodsService.findByOrderNo(orderNo);
    }

    @ResponseBody
    @RequestMapping("/deliver-save")
    @Transactional
    public boolean save(@RequestParam("ids[]") String[] ids, String expressNo, String orderNo) {
        if (StringUtils.isNotBlank(expressNo)) {
            long batch = -1;
            for (String id : ids) {
                PhOrderGoods orderGoods = phOrderGoodsService.findOne(Long.valueOf(id));
                if (orderGoods != null) {
                    if (batch < 0) {
                        batch = phOrderGoodsService.getMaxBatch(orderGoods.getOrderNo());
                    }
                    orderGoods.setMailNo(expressNo);
                    orderGoods.setBatch(batch + 1);
                    phOrderGoodsService.save(orderGoods);
                }
            }
        } else {
            phOrderInfoService.saveOrder(orderNo, ids);
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/deliver-cancel")
    public boolean cancel(String orderNo) {
        try {
            phOrderInfoService.cancel(orderNo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
