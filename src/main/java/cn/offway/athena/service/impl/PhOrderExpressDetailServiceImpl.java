package cn.offway.athena.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.athena.service.PhOrderExpressDetailService;

import cn.offway.athena.domain.PhOrderExpressDetail;
import cn.offway.athena.repository.PhOrderExpressDetailRepository;


/**
 * 订单物流详情Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhOrderExpressDetailServiceImpl implements PhOrderExpressDetailService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhOrderExpressDetailRepository phOrderExpressDetailRepository;
	
	@Override
	public PhOrderExpressDetail save(PhOrderExpressDetail phOrderExpressDetail){
		return phOrderExpressDetailRepository.save(phOrderExpressDetail);
	}
	
	@Override
	public PhOrderExpressDetail findOne(Long id){
		return phOrderExpressDetailRepository.findOne(id);
	}
}
