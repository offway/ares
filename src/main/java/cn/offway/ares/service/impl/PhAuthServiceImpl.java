package cn.offway.ares.service.impl;

import cn.offway.ares.domain.PhAuth;
import cn.offway.ares.domain.PhCode;
import cn.offway.ares.domain.PhCreditDetail;
import cn.offway.ares.domain.PhUserInfo;
import cn.offway.ares.dto.Template;
import cn.offway.ares.repository.PhAuthRepository;
import cn.offway.ares.service.PhAuthService;
import cn.offway.ares.service.PhCodeService;
import cn.offway.ares.service.PhCreditDetailService;
import cn.offway.ares.service.PhUserInfoService;
import cn.offway.ares.utils.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;


/**
 * 用户认证Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhAuthServiceImpl implements PhAuthService {

    private static Logger logger = LoggerFactory.getLogger(PhAuthServiceImpl.class);

    @Autowired
    private PhAuthRepository phAuthRepository;

    @Autowired
    private PhUserInfoService phUserInfoService;

    @Autowired
    private PhCreditDetailService phCreditDetailService;

    @Autowired
    private PhCodeService phCodeService;

    @Override
    public PhAuth save(PhAuth phAuth) {
        return phAuthRepository.save(phAuth);
    }

    @Override
    public PhAuth findOne(Long id) {
        return phAuthRepository.findOne(id);
    }

    @Override
    public Page<PhAuth> findByPage(final String status, final String nickName, final String unionid, Pageable page) {
        return phAuthRepository.findAll(new Specification<PhAuth>() {

            @Override
            public Predicate toPredicate(Root<PhAuth> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> params = new ArrayList<Predicate>();

                if (StringUtils.isNotBlank(status)) {
                    params.add(criteriaBuilder.equal(root.get("status"), status));
                }

                if (StringUtils.isNotBlank(nickName)) {
                    params.add(criteriaBuilder.like(root.get("nickname"), "%" + nickName + "%"));
                }

                if (StringUtils.isNotBlank(unionid)) {
                    params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
                }

                Predicate[] predicates = new Predicate[params.size()];
                criteriaQuery.where(params.toArray(predicates));
                return null;
            }
        }, page);
    }

    @Override
    public boolean authUpdate(Long id, String status, String approvalContent, Authentication authentication) {
        PhAuth phAuth = findOne(id);
        if (StringUtils.isNotBlank(approvalContent)) {
            phAuth.setApprovalContent(approvalContent);
        }
        phAuth.setStatus(status);
        phAuth.setApproval(new Date());
        phAuth.setApprover(authentication.getName());
        save(phAuth);
        PhUserInfo phUserInfo = phUserInfoService.findByUnionid(phAuth.getUnionid());
        if ("1".equals(status)) {
            phUserInfo.setIsAuth("1");
            phUserInfo.setPhone(phAuth.getPhone());
            phUserInfo.setPosition(phAuth.getPosition());
            phUserInfo.setIdcardObverse(phAuth.getIdcardObverse());
            phUserInfo.setIdcardPositive(phAuth.getIdcardPositive());
            phUserInfo.setRealName(phAuth.getRealName());
            phUserInfo.setInCert(phAuth.getInCert());
            phUserInfo.setCompanyName(phAuth.getCompanyName());
            phUserInfo.setCreditScore(50L);
            phUserInfoService.save(phUserInfo);

            PhCreditDetail creditDetail = new PhCreditDetail();
            creditDetail.setChannel("认证通过");
            creditDetail.setCreateName(phAuth.getApprover());
            creditDetail.setCreateTime(new Date());
            creditDetail.setOrderNo(null);
            creditDetail.setRemark(null);
            creditDetail.setScore(50L);
            creditDetail.setType("0");
            creditDetail.setUnionid(phAuth.getUnionid());
            phCreditDetailService.save(creditDetail);

        }
        String openid = phUserInfo.getMiniopenid();
        //发送订阅消息
        String result = "您的身份审核已通过";
        String content = "恭喜！可以借衣啦！";
        if ("2".equals(status)) {
            result = "您的身份审核未通过";
            content = approvalContent;
            PhCode phCode = phCodeService.findOne(phAuth.getCodeId());
            phCode.setStatus("0");
            phCodeService.save(phCode);
        }
        Map<String, Object> args = new HashMap<>();
        args.put("touser", openid);
        args.put("template_id", "Kp9iDQ5mUycHBTroqgGttJB5fQyxBZcmBpI-zTHAUwc");
        Map<String, Object> data = new HashMap<>();
        Map<String, String> k1 = new HashMap<>();
        k1.put("value", result);
        data.put("thing4", k1);
        Map<String, String> k2 = new HashMap<>();
        k2.put("value", content);
        data.put("thing5", k2);
        args.put("data", data);
        sendSubscribeMsg(args, getToken());
        return true;
    }

    /**
     * 获取 access_token
     */
    public static String getToken() {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxd39e2b6036febef9&secret=4ceed4a1664ce0692565e6e3277f4a9b";
        String result = HttpClientUtil.get(requestUrl);
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject != null) {
            return jsonObject.getString("access_token");
        } else {
            return "";
        }
    }

    public static void sendTemplateMsg(Template template, String token) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", token);
        String jsonString = template.toJSON();

        String result = HttpClientUtil.post(requestUrl, jsonString);
        JSONObject jsonResult = JSON.parseObject(result);
        if (jsonResult != null) {
            int errorCode = jsonResult.getIntValue("errcode");
            String errorMessage = jsonResult.getString("errmsg");
            if (errorCode == 0) {
                logger.info("模板消息发送成功:" + jsonResult);
            } else {
                logger.info("模板消息发送失败:" + errorCode + "," + errorMessage);
            }
        } else {
            logger.info("模板消息发送失败");
        }
    }

    public static void sendSubscribeMsg(Map<String, Object> template, String token) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", token);
        String jsonString = JSONObject.toJSONString(template);

        String result = HttpClientUtil.post(requestUrl, jsonString);
        JSONObject jsonResult = JSON.parseObject(result);
        if (jsonResult != null) {
            int errorCode = jsonResult.getIntValue("errcode");
            String errorMessage = jsonResult.getString("errmsg");
            if (errorCode == 0) {
                logger.info("订阅消息发送成功:" + jsonResult);
            } else {
                logger.info("订阅消息发送失败:" + errorCode + "," + errorMessage);
            }
        } else {
            logger.info("订阅消息发送失败");
        }
    }
}
