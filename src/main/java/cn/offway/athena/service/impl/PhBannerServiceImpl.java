package cn.offway.athena.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.athena.service.PhBannerService;

import cn.offway.athena.domain.PhBanner;
import cn.offway.athena.repository.PhBannerRepository;


/**
 * Banner管理Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhBannerServiceImpl implements PhBannerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhBannerRepository phBannerRepository;
	
	@Override
	public PhBanner save(PhBanner phBanner){
		return phBannerRepository.save(phBanner);
	}
	
	@Override
	public PhBanner findOne(Long id){
		return phBannerRepository.findOne(id);
	}
}
