package cn.offway.ares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.offway.ares.domain.PhOrderRemark;

import java.util.List;

/**
 * Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-10-08 13:56:14 Exp $
 */
public interface PhOrderRemarkRepository extends JpaRepository<PhOrderRemark,Long>,JpaSpecificationExecutor<PhOrderRemark> {

	/** 此处写一些自定义的方法 **/

	List<PhOrderRemark> findAllByOrdersId(String id);
}
