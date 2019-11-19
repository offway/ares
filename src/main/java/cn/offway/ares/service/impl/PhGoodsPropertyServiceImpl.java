package cn.offway.ares.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.ares.service.PhGoodsPropertyService;

import cn.offway.ares.domain.PhGoodsProperty;
import cn.offway.ares.repository.PhGoodsPropertyRepository;


/**
 * 商品属性Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-19 11:38:30 Exp $
 */
@Service
public class PhGoodsPropertyServiceImpl implements PhGoodsPropertyService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhGoodsPropertyRepository phGoodsPropertyRepository;
	
	@Override
	public PhGoodsProperty save(PhGoodsProperty phGoodsProperty){
		return phGoodsPropertyRepository.save(phGoodsProperty);
	}
	
	@Override
	public PhGoodsProperty findOne(Long id){
		return phGoodsPropertyRepository.findOne(id);
	}

	@Override
	public void delete(Long id){
		phGoodsPropertyRepository.delete(id);
	}

	@Override
	public List<PhGoodsProperty> save(List<PhGoodsProperty> entities){
		return phGoodsPropertyRepository.save(entities);
	}
}
