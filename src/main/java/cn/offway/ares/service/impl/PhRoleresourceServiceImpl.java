package cn.offway.ares.service.impl;

import java.util.List;

import cn.offway.ares.domain.PhRoleresource;
import cn.offway.ares.repository.PhRoleresourceRepository;
import cn.offway.ares.service.PhRoleresourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-10-15 16:49:00 Exp $
 */
@Service
public class PhRoleresourceServiceImpl implements PhRoleresourceService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhRoleresourceRepository phRoleresourceRepository;
	
	@Override
	public PhRoleresource save(PhRoleresource phRoleresource){
		return phRoleresourceRepository.save(phRoleresource);
	}
	
	@Override
	public PhRoleresource findOne(Long id){
		return phRoleresourceRepository.findOne(id);
	}
	
	@Override
	public List<Long> findSourceIdByRoleId(Long roleId){
		return phRoleresourceRepository.findSourceIdByRoleId(roleId);
	}
}
