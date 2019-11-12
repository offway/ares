package cn.offway.ares.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import cn.offway.ares.domain.PhBrand;
import cn.offway.ares.domain.PhOrderInfo;
import cn.offway.ares.domain.PhShowImage;
import cn.offway.ares.domain.PhShowImageFilter;
import cn.offway.ares.service.PhBrandService;
import cn.offway.ares.service.PhOrderInfoService;
import cn.offway.ares.service.PhShowImageFilterService;
import cn.offway.ares.service.PhShowImageService;

/**
 * 返图查看
 * @author wn
 *
 */
@Controller
public class ShowImageFilterController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PhShowImageFilterService PhShowImageFilterService;
	
	@Autowired
	private PhShowImageService phShowImageService;
	
	@Autowired
	private PhBrandService phBrandService;
	
	@Autowired
	private PhOrderInfoService phOrderInfoService;
	
	@RequestMapping("/showImageFilter.html")
	public String showImageInfo(ModelMap map,Authentication authentication){
		
		PhAdmin phAdmin = (PhAdmin)authentication.getPrincipal();
		List<Long> brandIds = phAdmin.getBrandIds();
		
		map.addAttribute("brands", phBrandService.findByIds(brandIds));
		return "showImageFilter";
	}
	
	/**
	 * 查询数据
	 * @param request
	 * @param showImage
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/showImageFilter-data")
	public Map<String, Object> showImageData(HttpServletRequest request,Long brandId,Authentication authentication){
		
		String sortCol = request.getParameter("iSortCol_0");
		String sortName = request.getParameter("mDataProp_"+sortCol);
		String sortDir = request.getParameter("sSortDir_0");
		int sEcho = Integer.parseInt(request.getParameter("sEcho"));
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		int iDisplayLength  = Integer.parseInt(request.getParameter("iDisplayLength"));
		
		PhAdmin phAdmin = (PhAdmin)authentication.getPrincipal();
		List<Long> brandIds = phAdmin.getBrandIds();
		Page<PhShowImageFilter> pages = PhShowImageFilterService.findByPage(brandId,brandIds,new PageRequest(iDisplayStart==0?0:iDisplayStart/iDisplayLength, iDisplayLength<0?9999999:iDisplayLength,Direction.fromString(sortDir),sortName));
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
	@RequestMapping("/showImageFilter-save")
	public boolean showImageCheck(Long showImgId,String image,String brandIds,String starName){
		Date now = new Date();
		
		PhShowImage phShowImage = phShowImageService.findOne(showImgId);
		String orderNo = phShowImage.getOrderNo();
		PhOrderInfo phOrderInfo = phOrderInfoService.findByOrderNo(orderNo);
		Date useDate = phOrderInfo.getUseDate();
		
		List<PhShowImageFilter> imageFilters = new ArrayList<>();
		String[] brandIdArray =  brandIds.split(",");
		for (String brandIdStr : brandIdArray) {
			if(StringUtils.isNotBlank(brandIdStr)){
				PhShowImageFilter imageFilter = new PhShowImageFilter();
				Long brandId = Long.parseLong(brandIdStr);
				PhBrand phBrand = phBrandService.findOne(brandId);
				imageFilter.setBrandId(brandId);
				imageFilter.setBrandLogo(phBrand.getLogo());
				imageFilter.setBrandName(phBrand.getName());
				imageFilter.setCreateTime(now);
				imageFilter.setShowImage(image);
				imageFilter.setStarName(starName);
				imageFilter.setUseDate(useDate);
				imageFilters.add(imageFilter);
			}
		}
		PhShowImageFilterService.save(imageFilters);
		return true;
	}
	
	@ResponseBody
	@RequestMapping("/showImageFilter-exists")
	public String exists(Long showImgId){
		
		PhShowImage phShowImage = phShowImageService.findOne(showImgId);
		String showImage = phShowImage.getShowImage();
		String[] showImageArray = showImage.split(",");
		for (String si : showImageArray) {
			int count = PhShowImageFilterService.countByShowImage(si);
			if(count>0){
				showImage = showImage.replace(si, "");
			}
		}
		
		return showImage;
	}
	
	
	
	
}
