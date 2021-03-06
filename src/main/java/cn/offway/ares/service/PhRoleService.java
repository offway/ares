package cn.offway.ares.service;

import java.util.List;

import cn.offway.ares.domain.PhRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-10-15 16:49:00 Exp $
 */
public interface PhRoleService{

	PhRole save(PhRole phRole);
	
	PhRole findOne(Long id);

	Page<PhRole> findByPage(String name, Pageable page);

	void deleteRole(String ids) throws Exception;

	void save(PhRole phRole, Long[] resourceIds) throws Exception;

	List<PhRole> findAll();
}
