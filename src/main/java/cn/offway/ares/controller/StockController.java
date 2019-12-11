package cn.offway.ares.controller;

import cn.offway.ares.domain.PhAdmin;
import cn.offway.ares.domain.PhGoodsStock;
import cn.offway.ares.properties.QiniuProperties;
import cn.offway.ares.service.PhBrandService;
import cn.offway.ares.service.PhGoodsService;
import cn.offway.ares.service.PhGoodsStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 库存管理
 *
 * @author wn
 */
@Controller
public class StockController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PhGoodsStockService phGoodsStockService;

    @Autowired
    private PhBrandService phBrandService;

    @Autowired
    private PhGoodsService phGoodsService;

    @Autowired
    private QiniuProperties qiniuProperties;


    /**
     * 库存
     */
    @RequestMapping("/stock.html")
    public String stock(ModelMap map, Authentication authentication) {
        map.addAttribute("qiniuUrl", qiniuProperties.getUrl());

        PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
        List<Long> brandIds = phAdmin.getBrandIds();

        map.addAttribute("brands", phBrandService.findByIds(brandIds));
        return "stock";
    }

    /**
     * 查询数据
     */
    @ResponseBody
    @RequestMapping("/stock-data")
    public Map<String, Object> stockData(HttpServletRequest request, String sku, Long brandId, String brandName, Long goodsId, String goodsName, String isOffway, String color, String size, Authentication authentication, int sEcho, int iDisplayStart, int iDisplayLength) {
        String sortCol = request.getParameter("iSortCol_0");
        String sortName = request.getParameter("mDataProp_" + sortCol);
        String sortDir = request.getParameter("sSortDir_0");
        PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
        List<Long> brandIds = phAdmin.getBrandIds();
        PageRequest pr = new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName);
        Page<PhGoodsStock> pages = phGoodsStockService.findByPage(sku.trim(), brandId, null != brandName ? brandName.trim() : brandName, goodsId, null != goodsName ? goodsName.trim() : goodsName, isOffway.trim(), color.trim(), size.trim(), brandIds, pr);
        // 为操作次数加1，必须这样做
        int initEcho = sEcho + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("sEcho", initEcho);
        map.put("iTotalRecords", pages.getTotalElements());//数据总条数  
        map.put("iTotalDisplayRecords", pages.getTotalElements());//显示的条数  
        map.put("aData", pages.getContent());//数据集合 
        return map;
    }

    @ResponseBody
    @PostMapping("/stock-one")
    public PhGoodsStock findOne(Long id) {
        return phGoodsStockService.findOne(id);
    }

    @ResponseBody
    @PostMapping("/stock-image")
    public String image(String color, Long goodsId) {
        return phGoodsStockService.findImage(color, goodsId);
    }

    @ResponseBody
    @PostMapping("/stock-delete")
    public boolean delete(@RequestParam("ids[]") Long[] ids) {
        int count = phGoodsStockService.deleteByIds(Arrays.asList(ids));
        return count > 0;
    }
}
