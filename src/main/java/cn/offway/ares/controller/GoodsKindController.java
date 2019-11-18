package cn.offway.ares.controller;

import cn.offway.ares.domain.PhGoodsCategory;
import cn.offway.ares.domain.PhGoodsKind;
import cn.offway.ares.domain.PhGoodsType;
import cn.offway.ares.properties.QiniuProperties;
import cn.offway.ares.service.PhGoodsCategoryService;
import cn.offway.ares.service.PhGoodsKindService;
import cn.offway.ares.service.PhGoodsTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class GoodsKindController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private QiniuProperties qiniuProperties;
    @Autowired
    private PhGoodsCategoryService goodsCategoryService;
    @Autowired
    private PhGoodsTypeService goodsTypeService;
    @Autowired
    private PhGoodsKindService goodsKindService;

    @RequestMapping("/goodsKind.html")
    public String index(ModelMap map, Long id) {
        map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
        map.addAttribute("theId", id);
        PhGoodsCategory goodsCategory = goodsCategoryService.findOne(id);
        if (goodsCategory != null) {
            map.addAttribute("theName", goodsCategory.getName());
        }
        return "goodsKind_index";
    }

    @ResponseBody
    @RequestMapping("/goodsKind_list")
    public Map<String, Object> getList(HttpServletRequest request) {
        int sEcho = Integer.parseInt(request.getParameter("sEcho"));
        int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
        int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
        long id = Long.valueOf(request.getParameter("id"));
        Sort sort = new Sort("sort");
        Page<PhGoodsKind> pages = goodsKindService.findByPid(id, new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, sort));
        int initEcho = sEcho + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("sEcho", initEcho);
        map.put("iTotalRecords", pages.getTotalElements());//数据总条数
        map.put("iTotalDisplayRecords", pages.getTotalElements());//显示的条数
        map.put("aData", pages.getContent());//数据集合
        return map;
    }

    @ResponseBody
    @RequestMapping("/goodsKind_del")
    public boolean delete(@RequestParam("ids[]") Long[] ids) {
        for (Long id : ids) {
            goodsKindService.delete(id);
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/goodsKind_save")
    public boolean save(PhGoodsKind goodsKind) {
        goodsKind.setCreateTime(new Date());
        PhGoodsCategory goodsCategory = goodsCategoryService.findOne(goodsKind.getGoodsCategory());
        if (goodsCategory != null) {
            goodsKind.setGoodsCategoryName(goodsCategory.getName());
            goodsKind.setGoodsCategory(goodsCategory.getId());
            PhGoodsType goodsType = goodsTypeService.findOne(goodsCategory.getGoodsType());
            if (goodsType != null) {
                goodsKind.setGoodsTypeName(goodsType.getName());
                goodsKind.setGoodsType(goodsType.getId());
            }
        }
        goodsKindService.save(goodsKind);
        return true;
    }

    @ResponseBody
    @RequestMapping("/goodsKind_get")
    public PhGoodsKind get(Long id) {
        return goodsKindService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/goodsKind_find")
    public Map<String, Object> find(Long id) {
        Map<String, Object> map = new HashMap<>();
        PhGoodsKind goodsKind = goodsKindService.findOne(id);
        if (goodsKind != null) {
            map.put("main", goodsKind);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/goodsKind_top")
    public boolean top(Long id, Long sort, Long theId) {
        PhGoodsKind goodsKind = goodsKindService.findOne(id);
        if (goodsKind != null) {
            goodsKindService.resort(sort, theId);
            goodsKind.setSort(sort);
            goodsKindService.save(goodsKind);
        }
        return true;
    }
}
