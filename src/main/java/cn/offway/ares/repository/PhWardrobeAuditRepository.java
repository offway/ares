package cn.offway.ares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.offway.ares.domain.PhWardrobeAudit;

/**
 * 衣柜审核Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-21 17:04:14 Exp $
 */
public interface PhWardrobeAuditRepository extends JpaRepository<PhWardrobeAudit,Long>,JpaSpecificationExecutor<PhWardrobeAudit> {

	/** 此处写一些自定义的方法 **/
}
