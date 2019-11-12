package cn.offway.ares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.offway.ares.domain.PhOfflineOrders;

/**
 * Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-09-27 13:41:55 Exp $
 */
public interface PhOfflineOrdersRepository extends JpaRepository<PhOfflineOrders,Long>,JpaSpecificationExecutor<PhOfflineOrders> {

	/** 此处写一些自定义的方法 **/
}
