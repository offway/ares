package cn.offway.ares.controller;

import cn.offway.ares.domain.PhOrderGoods;
import cn.offway.ares.domain.VRanking;
import cn.offway.ares.properties.QiniuProperties;
import cn.offway.ares.service.PhBrandService;
import cn.offway.ares.service.PhOrderGoodsService;
import cn.offway.ares.service.VRankingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 借衣排行
 * @author wn
 *
 */
@Controller
public class RankingController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PhBrandService phBrandService;
	
	@Autowired
	private QiniuProperties qiniuProperties;

	@Autowired
	private VRankingService vRankingService;

	@Autowired
	private PhOrderGoodsService orderGoodsService;
	/**
	 * 借衣排行
	 * @param map
	 * @return
	 */
	@RequestMapping("/ranking.html")
	public String brand(ModelMap map){
		map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
		return "ranking";
	}
	
	/**
	 * 查询数据
	 */
	@ResponseBody
	@RequestMapping("/ranking-data")
	public Map<String, Object> stockData(HttpServletRequest request, String brandId, int sEcho, int iDisplayStart, int iDisplayLength) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "sumgoods"));
		PageRequest pr = new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, sort);
		Page<VRanking> pages = vRankingService.findAll(pr, brandId);
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
	@RequestMapping("/ranking-countbrand")
	public Map<String, Object> rankingBybrand(HttpServletRequest request,String brandId){
		String sortCol = request.getParameter("iSortCol_0");
		String sortName = request.getParameter("mDataProp_"+sortCol);
		String sortDir = request.getParameter("sSortDir_0");
		int sEcho = Integer.parseInt(request.getParameter("sEcho"));
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		int iDisplayLength  = Integer.parseInt(request.getParameter("iDisplayLength"));
		Page<PhOrderGoods> pages = orderGoodsService.findByBrandId(brandId, new PageRequest(iDisplayStart==0?0:iDisplayStart/iDisplayLength, iDisplayLength<0?9999999:iDisplayLength,Direction.fromString(sortDir),sortName));
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
