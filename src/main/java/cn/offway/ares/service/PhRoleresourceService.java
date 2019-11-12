package cn.offway.ares.service;

import java.util.List;

import cn.offway.ares.domain.PhRoleresource;

/**
 * Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-10-15 16:49:00 Exp $
 */
public interface PhRoleresourceService{

	PhRoleresource save(PhRoleresource phRoleresource);
	
	PhRoleresource findOne(Long id);

	List<Long> findSourceIdByRoleId(Long roleId);
}
