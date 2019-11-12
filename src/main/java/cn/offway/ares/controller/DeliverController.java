package cn.offway.ares.controller;

import java.util.HashMap;
import java.util.List;
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

import cn.offway.ares.domain.PhAdmin;
import cn.offway.ares.domain.PhOrderExpressInfo;
import cn.offway.ares.domain.PhOrderGoods;
import cn.offway.ares.domain.VOrder;
import cn.offway.ares.service.PhBrandService;
import cn.offway.ares.service.PhOrderExpressInfoService;
import cn.offway.ares.service.PhOrderGoodsService;
import cn.offway.ares.service.PhOrderInfoService;
import cn.offway.ares.service.VOrderService;
import cn.offway.ares.utils.JsonResult;

/**
 * 发货
 * @author wn
 *
 */
@Controller
public class DeliverController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private VOrderService vOrderService;
	
	@Autowired
	private PhOrderExpressInfoService phOrderExpressInfoService;
	
	@Autowired
	private PhOrderGoodsService phOrderGoodsService;
	
	@Autowired
	private PhOrderInfoService phOrderInfoService;
	
	@Autowired
	private PhBrandService phBrandService;
	
	@RequestMapping("/deliver.html")
	public String deliver(ModelMap map,Authentication authentication){
		
		PhAdmin phAdmin = (PhAdmin)authentication.getPrincipal();
		List<Long> brandIds = phAdmin.getBrandIds();
		
		map.addAttribute("brands", phBrandService.findByIds(brandIds));
		return "deliver";
	}
	
	/**
	 * 查询数据
	 * @param request
	 * @param deliver
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deliver-data")
	public Map<String, Object> deliverData(HttpServletRequest request,String orderNo, 
			String realName, String position,String unionid,Long brandId,String isOffway,Authentication authentication){
		
		String sortCol = request.getParameter("iSortCol_0");
		String sortName = request.getParameter("mDataProp_"+sortCol);
		String sortDir = request.getParameter("sSortDir_0");
		int sEcho = Integer.parseInt(request.getParameter("sEcho"));
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		int iDisplayLength  = Integer.parseInt(request.getParameter("iDisplayLength"));
		
		PhAdmin phAdmin = (PhAdmin)authentication.getPrincipal();
		List<Long> brandIds = phAdmin.getBrandIds();
		Page<VOrder> pages = vOrderService.findByPage(realName.trim(),position.trim(),orderNo.trim(),null!=unionid?unionid.trim():unionid,brandId,isOffway, brandIds,new PageRequest(iDisplayStart==0?0:iDisplayStart/iDisplayLength, iDisplayLength<0?9999999:iDisplayLength,Direction.fromString(sortDir),sortName));
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
	@RequestMapping("/deliver-one")
	public VOrder findByOrderNo(String orderNo){
		return vOrderService.findByOrderNo(orderNo);
	}
	
	@ResponseBody
	@RequestMapping("/deliver-updateAddr")
	public boolean updateAddr(String id,String toRealName,String toPhone,String toContent){
		PhOrderExpressInfo phOrderExpressInfo =  phOrderExpressInfoService.findByOrderNoAndType(id, "0");
		phOrderExpressInfo.setToContent(toContent);
		phOrderExpressInfo.setToRealName(toRealName);
		phOrderExpressInfo.setToPhone(toPhone);
		phOrderExpressInfoService.save(phOrderExpressInfo);
		return true;
	}
	
	@ResponseBody
	@RequestMapping("/deliver-goods")
	public List<PhOrderGoods> phOrderGoods(String orderNo){
		return phOrderGoodsService.findByOrderNo(orderNo);
	}
	
	@ResponseBody
	@RequestMapping("/deliver-save")
	public JsonResult save(String orderNo){
		return phOrderInfoService.saveOrder(orderNo);
	}
	
	@ResponseBody
	@RequestMapping("/deliver-cancel")
	public boolean cancel(String orderNo){
		try {
			phOrderInfoService.cancel(orderNo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
}
