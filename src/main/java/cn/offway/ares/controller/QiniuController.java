package cn.offway.ares.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.offway.ares.properties.QiniuProperties;
import cn.offway.ares.service.QiniuService;

/**
 * 七牛
 * @author wn
 *
 */
@RestController
@RequestMapping("/qiniu")
public class QiniuController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QiniuProperties qiniuProperties;
	
	@Autowired
	private QiniuService qiniuService;

	@GetMapping("/token")
	public String token(){
		return qiniuService.token();
	}
	
	@PostMapping("/delete")
	public boolean delete(String url){
		return qiniuService.qiniuDelete(url.replace(qiniuProperties.getUrl()+"/", ""));
	}
}
