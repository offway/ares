package cn.offway.ares.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.offway.ares.domain.PhUserInfo;
import cn.offway.ares.service.PhUserInfoService;

/**
 * 用户查询
 * @author wn
 *
 */
@Controller
public class PhUserController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PhUserInfoService phUserInfoService;
	
	@RequestMapping("/phUsers.html")
	public String phUsers(ModelMap map){
		return "phUsers";
	}
	
	/**
	 * 查询数据
	 * @param request
	 * @param phUsers
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/phUsers-data")
	public Map<String, Object> phUsersData(HttpServletRequest request,String nickname, String unionid, String phone, String isAuth){
		
		String sortCol = request.getParameter("iSortCol_0");
		String sortName = request.getParameter("mDataProp_"+sortCol);
		String sortDir = request.getParameter("sSortDir_0");
		int sEcho = Integer.parseInt(request.getParameter("sEcho"));
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		int iDisplayLength  = Integer.parseInt(request.getParameter("iDisplayLength"));
		Page<PhUserInfo> pages = phUserInfoService.findByPage(nickname.trim(),unionid.trim(),phone.trim(),isAuth.trim(), new PageRequest(iDisplayStart==0?0:iDisplayStart/iDisplayLength, iDisplayLength<0?9999999:iDisplayLength,Direction.fromString(sortDir),sortName));
		 // 为操作次数加1，必须这样做  
        int initEcho = sEcho + 1;  
        Map<String, Object> map = new HashMap<>();
		map.put("sEcho", initEcho);  
        map.put("iTotalRecords", pages.getTotalElements());//数据总条数  
        map.put("iTotalDisplayRecords", pages.getTotalElements());//显示的条数  
        map.put("aData", pages.getContent());//数据集合 
		return map;
	}
	
	
}
