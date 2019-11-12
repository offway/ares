package cn.offway.ares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.offway.ares.domain.PhOfflineRemark;

/**
 * 线下订单留言Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-10-09 13:33:39 Exp $
 */
public interface PhOfflineRemarkRepository extends JpaRepository<PhOfflineRemark,Long>,JpaSpecificationExecutor<PhOfflineRemark> {

	/** 此处写一些自定义的方法 **/
}
