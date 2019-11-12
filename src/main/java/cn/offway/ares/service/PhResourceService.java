package cn.offway.ares.service;

import java.util.List;
import java.util.Set;

import cn.offway.ares.domain.PhResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-10-15 16:49:00 Exp $
 */
public interface PhResourceService{

	PhResource save(PhResource phResource);
	
	PhResource findOne(Long id);

	Set<String> findUrlsByAdminId(Long adminId);

	List<PhResource> findByAdminId(Long adminId);

	Page<PhResource> findByPage(String name, String link, Long parentId, Pageable page);

	void deleteResource(String ids) throws Exception;

	List<PhResource> findByParentId(Long parentId);

	List<PhResource> findByParentIdNotNull();
}
