package cn.offway.ares.controller;

import cn.offway.ares.domain.PhAdmin;
import cn.offway.ares.domain.PhUserInfo;
import cn.offway.ares.domain.PhWardrobe;
import cn.offway.ares.domain.PhWardrobeAudit;
import cn.offway.ares.dto.Template;
import cn.offway.ares.dto.TemplateParam;
import cn.offway.ares.service.PhBrandService;
import cn.offway.ares.service.PhUserInfoService;
import cn.offway.ares.service.PhWardrobeAuditService;
import cn.offway.ares.service.PhWardrobeService;
import cn.offway.ares.service.impl.PhAuthServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AuditController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PhWardrobeAuditService wardrobeAuditService;
    @Autowired
    private PhWardrobeService wardrobeService;
    @Autowired
    private PhBrandService brandService;
    @Autowired
    private PhUserInfoService userInfoService;

    @RequestMapping("/audit.html")
    public String index(ModelMap map, Authentication authentication) {
        PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
        if (phAdmin.getRoleIds().contains(BigInteger.ONE)) {
            map.addAttribute("brands", brandService.findAll());
        } else {
            List<Long> brandIds = phAdmin.getBrandIds();
            map.addAttribute("brands", brandService.findByIds(brandIds));
        }
        return "audit_index";
    }

    @ResponseBody
    @RequestMapping("/audit_list")
    public Map<String, Object> getStockList(int sEcho, int iDisplayStart, int iDisplayLength, String brandId, String goodsName, String goodsId, String state, Authentication authentication) {
        Sort sort = new Sort("id");
        PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
        List<Long> brandIds;
        if (phAdmin.getRoleIds().contains(BigInteger.ONE)) {
            brandIds = null;
        } else {
            brandIds = phAdmin.getBrandIds();
        }
        PageRequest pr = new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, sort);
        Page<PhWardrobeAudit> pages = wardrobeAuditService.listAll(brandId, goodsName, goodsId, state, brandIds, pr);
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
        if (obj != null) {
            PhWardrobe wardrobe = wardrobeService.findOne(obj.getWardrobeId());
            obj.setState("1");
            wardrobeAuditService.save(obj);
            wardrobe.setState("1");
            wardrobeService.save(wardrobe);
            PhUserInfo userInfo = userInfoService.findByUnionid(obj.getUnionid());
            if (userInfo != null) {
                sendMsg(userInfo.getMiniopenid(), obj.getFormId(), null);
            }
        }
        return true;
    }

    private void sendMsg(String openid, String formid, String approvalContent) {
        // 模块消息配置
        Template tem = new Template();
        tem.setTemplateId("3XfYDBQWMwRfEsvmRhemNtZVy-j5dFoNPXoCz7t4QwI");
        tem.setFormId(formid);
        tem.setTopColor("#00DD00");
        tem.setToUser(openid);
        tem.setPage("pages/index/index");
        String result = "审核通知";
        String content = "你在OFFWAY MODE SHOWROOM申请的服装已通过审核，请及时提交订单借衣。";
        if (StringUtils.isNotBlank(approvalContent)) {
            result = " 审核失败";
            content = "您在OFFWAY MODE SHOWROOM申请的借衣未通过审核，理由：" + approvalContent;
        }
        List<TemplateParam> paras = new ArrayList<TemplateParam>();
        paras.add(new TemplateParam("keyword1", result, "#0044BB"));
        paras.add(new TemplateParam("keyword2", content, "#0044BB"));
        tem.setTemplateParamList(paras);
        // 推送模版消息
        PhAuthServiceImpl.sendTemplateMsg(tem, PhAuthServiceImpl.getToken());
    }

    @ResponseBody
    @RequestMapping("/audit_down")
    public boolean deny(Long id, String str) {
        PhWardrobeAudit obj = wardrobeAuditService.findOne(id);
        if (obj != null) {
            PhWardrobe wardrobe = wardrobeService.findOne(obj.getWardrobeId());
            obj.setState("2");
            obj.setReason(str);
            wardrobeAuditService.save(obj);
            wardrobe.setState("2");
            wardrobeService.save(wardrobe);
            PhUserInfo userInfo = userInfoService.findByUnionid(obj.getUnionid());
            if (userInfo != null) {
                sendMsg(userInfo.getMiniopenid(), obj.getFormId(), str);
            }
        }
        return true;
    }
}
