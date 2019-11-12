package cn.offway.ares.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.offway.ares.domain.PhBrandadmin;


/**
 * Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-04-01 11:26:00 Exp $
 */
public interface PhBrandadminRepository extends JpaRepository<PhBrandadmin,Long>,JpaSpecificationExecutor<PhBrandadmin> {

	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="delete from ph_brandadmin where admin_id =?1")
	int deleteByAdminId(Long id);
	
	@Query(nativeQuery=true,value="select brand_id from ph_brandadmin where admin_id =?1")
	List<Long> findBrandIdByAdminId(Long adminId);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="delete from ph_brandadmin where admin_id in(?1)")
	int deleteByAdminIds(List<Long> ids);
}
