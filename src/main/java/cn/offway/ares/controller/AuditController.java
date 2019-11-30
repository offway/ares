package cn.offway.ares.controller;

import cn.offway.ares.domain.PhWardrobe;
import cn.offway.ares.domain.PhWardrobeAudit;
import cn.offway.ares.service.PhWardrobeAuditService;
import cn.offway.ares.service.PhWardrobeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuditController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PhWardrobeAuditService wardrobeAuditService;
    @Autowired
    private PhWardrobeService wardrobeService;

    @RequestMapping("/audit.html")
    public String index(ModelMap map) {
        return "audit_index";
    }

    @ResponseBody
    @RequestMapping("/audit_list")
    public Map<String, Object> getStockList(int sEcho, int iDisplayStart, int iDisplayLength, String brandId, String goodsName, String goodsId, String state) {
        Sort sort = new Sort("id");
        PageRequest pr = new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, sort);
        Page<PhWardrobeAudit> pages = wardrobeAuditService.listAll(brandId, goodsName, goodsId, state, pr);
        int initEcho = sEcho + 1;
        Map<String, Object> map = new HashMap<>();
        map.put("sEcho", initEcho);
        map.put("iTotalRecords", pages.getTotalElements());//数据总条数
        map.put("iTotalDisplayRecords", pages.getTotalElements());//显示的条数
        map.put("aData", pages.getContent());//数据集合
        return map;
    }

    @ResponseBody
    @RequestMapping("/audit_findOne")
    public PhWardrobeAudit findOne(Long id) {
        return wardrobeAuditService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/audit_up")
    public boolean allow(Long id) {
        PhWardrobeAudit obj = wardrobeAuditService.findOne(id);
        PhWardrobe wardrobe = wardrobeService.findOne(obj.getWardrobeId());
        if (obj != null) {
            obj.setState("1");
            wardrobeAuditService.save(obj);
            wardrobe.setState("1");
            wardrobeService.save(wardrobe);
        }
        return true;
    }

    @ResponseBody
    @RequestMapping("/audit_down")
    public boolean deny(Long id, String str) {
        PhWardrobeAudit obj = wardrobeAuditService.findOne(id);
        PhWardrobe wardrobe = wardrobeService.findOne(obj.getWardrobeId());
        if (obj != null) {
            obj.setState("2");
            obj.setReason(str);
            wardrobeAuditService.save(obj);
            wardrobe.setState("2");
            wardrobeService.save(wardrobe);
        }
        return true;
    }
}
