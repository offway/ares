package cn.offway.ares.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.offway.ares.domain.PhCreditDetail;
import cn.offway.ares.domain.PhOrderInfo;
import cn.offway.ares.domain.PhUserInfo;
import cn.offway.ares.service.PhCreditDetailService;
import cn.offway.ares.service.PhOrderInfoService;
import cn.offway.ares.service.PhUserInfoService;

/**
 * 信用管理
 * @author wn
 *
 */
@Controller
public class CreditController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PhCreditDetailService phCreditDetailService;
	
	@Autowired
	private PhUserInfoService phUserInfoService;
	
	@Autowired
	private PhOrderInfoService phOrderInfoService;
	
	@RequestMapping("/credit.html")
	public String credit(ModelMap map){
		return "credit";
	}
	
	/**
	 * 查询数据
	 * @param request
	 * @param credit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/credit-data")
	public Map<String, Object> creditData(HttpServletRequest request,String orderNo, String unionid,String type){
		
		String sortCol = request.getParameter("iSortCol_0");
		String sortName = request.getParameter("mDataProp_"+sortCol);
		String sortDir = request.getParameter("sSortDir_0");
		int sEcho = Integer.parseInt(request.getParameter("sEcho"));
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		int iDisplayLength  = Integer.parseInt(request.getParameter("iDisplayLength"));
		Page<PhCreditDetail> pages = phCreditDetailService.findByPage(orderNo.trim(),unionid.trim(),type.trim(), new PageRequest(iDisplayStart==0?0:iDisplayStart/iDisplayLength, iDisplayLength<0?9999999:iDisplayLength,Direction.fromString(sortDir),sortName));
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
	@RequestMapping("/credit-save")
	public boolean creditSave(String orderNo,String type,Long score,String channel,Authentication authentication){
		PhOrderInfo phOrderInfo = phOrderInfoService.findByOrderNo(orderNo);
		if(null == phOrderInfo){
			return false;
		}
		PhCreditDetail phCreditDetail = new PhCreditDetail();
		phCreditDetail.setType(type);
		phCreditDetail.setScore(score);
		phCreditDetail.setChannel(channel);
		phCreditDetail.setCreateName(authentication.getName());
		phCreditDetail.setCreateTime(new Date());
		phCreditDetail.setOrderNo(orderNo);
		phCreditDetail.setUnionid(phOrderInfo.getUnionid());
		phCreditDetailService.save(phCreditDetail);
		
		
		PhUserInfo phUserInfo = phUserInfoService.findByUnionid(phOrderInfo.getUnionid());
		Long creditScore = phUserInfo.getCreditScore();
		if("0".equals(type)){
			creditScore = creditScore.longValue()+score.longValue();
		}else{
			creditScore = creditScore.longValue()-score.longValue();

		}
		phUserInfo.setCreditScore(creditScore);
		phUserInfoService.save(phUserInfo);
		return true;
	}
}
