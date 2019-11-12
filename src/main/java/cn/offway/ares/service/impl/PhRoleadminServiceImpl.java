package cn.offway.ares.service.impl;

import java.util.List;

import cn.offway.ares.domain.PhRoleadmin;
import cn.offway.ares.repository.PhRoleadminRepository;
import cn.offway.ares.service.PhRoleadminService;
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
public class PhRoleadminServiceImpl implements PhRoleadminService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhRoleadminRepository phRoleadminRepository;
	
	@Override
	public PhRoleadmin save(PhRoleadmin phRoleadmin){
		return phRoleadminRepository.save(phRoleadmin);
	}
	
	@Override
	public PhRoleadmin findOne(Long id){
		return phRoleadminRepository.findOne(id);
	}
	
	@Override
	public List<Long> findRoleIdByAdminId(Long adminId){
		return phRoleadminRepository.findRoleIdByAdminId(adminId);
	}
}
