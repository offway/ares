package cn.offway.ares.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.ares.service.PhAddressService;

import cn.offway.ares.domain.PhAddress;
import cn.offway.ares.repository.PhAddressRepository;


/**
 * 地址管理Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhAddressServiceImpl implements PhAddressService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhAddressRepository phAddressRepository;
	
	@Override
	public PhAddress save(PhAddress phAddress){
		return phAddressRepository.save(phAddress);
	}
	
	@Override
	public PhAddress findOne(Long id){
		return phAddressRepository.findOne(id);
	}
	
	@Override
	public void delete(Long id){
		 phAddressRepository.delete(id);
	}
}
