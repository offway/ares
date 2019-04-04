package cn.offway.athena.controller;

import java.util.Date;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.offway.athena.domain.PhBrand;
import cn.offway.athena.properties.QiniuProperties;
import cn.offway.athena.service.PhBrandService;


/**
 * 品牌管理
 * @author wn
 *
 */
@Controller
public class BrandController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PhBrandService phBrandService;
	
	@Autowired
	private QiniuProperties qiniuProperties;
	
	/**
	 * 品牌
	 * @param map
	 * @return
	 */
	@RequestMapping("/brand.html")
	public String brand(ModelMap map){
		map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
		return "brand";
	}
	
	/**
	 * 查询数据
	 * @param request
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/brand-data")
	public Map<String, Object> stockData(HttpServletRequest request,Long brandId,String brandName){
		
		String sortCol = request.getParameter("iSortCol_0");
		String sortName = request.getParameter("mDataProp_"+sortCol);
		String sortDir = request.getParameter("sSortDir_0");
		int sEcho = Integer.parseInt(request.getParameter("sEcho"));
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		int iDisplayLength  = Integer.parseInt(request.getParameter("iDisplayLength"));
		Page<PhBrand> pages = phBrandService.findByPage(brandId,null!=brandName?brandName.trim():brandName, new PageRequest(iDisplayStart==0?0:iDisplayStart/iDisplayLength, iDisplayLength<0?9999999:iDisplayLength,Direction.fromString(sortDir),sortName));
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
	@PostMapping("/brand-save")
	public boolean save(PhBrand phBrand){
		try {
			phBrand.setCreateTime(new Date());
			phBrandService.save(phBrand);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存品牌异常,{}",JSON.toJSONString(phBrand),e);
			return false;
		}
		
	}
	
	@ResponseBody
	@PostMapping("/brand-one")
	public PhBrand findOne(Long id){
		return phBrandService.findOne(id);
	}
	
	
	@ResponseBody
	@GetMapping("/brand-showImgId")
	public List<PhBrand> findByShowImgId(Long showImgId){
		return phBrandService.findByShowImgId(showImgId);
	}
	
	
}
