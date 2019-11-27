package cn.offway.ares.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.ares.service.PhAddressBrandService;

import cn.offway.ares.domain.PhAddressBrand;
import cn.offway.ares.repository.PhAddressBrandRepository;


/**
 * 品牌地址管理Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-27 18:02:40 Exp $
 */
@Service
public class PhAddressBrandServiceImpl implements PhAddressBrandService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhAddressBrandRepository phAddressBrandRepository;
	
	@Override
	public PhAddressBrand save(PhAddressBrand phAddressBrand){
		return phAddressBrandRepository.save(phAddressBrand);
	}
	
	@Override
	public PhAddressBrand findOne(Long id){
		return phAddressBrandRepository.findOne(id);
	}

	@Override
	public void delete(Long id){
		phAddressBrandRepository.delete(id);
	}

	@Override
	public List<PhAddressBrand> save(List<PhAddressBrand> entities){
		return phAddressBrandRepository.save(entities);
	}
}
