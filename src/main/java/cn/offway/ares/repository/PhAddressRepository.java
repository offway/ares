package cn.offway.ares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.offway.ares.domain.PhAddress;

/**
 * 地址管理Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhAddressRepository extends JpaRepository<PhAddress,Long>,JpaSpecificationExecutor<PhAddress> {

	/** 此处写一些自定义的方法 **/
}
