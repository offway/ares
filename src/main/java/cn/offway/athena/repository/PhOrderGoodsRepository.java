package cn.offway.athena.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.offway.athena.domain.PhOrderGoods;

/**
 * 订单商品Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhOrderGoodsRepository extends JpaRepository<PhOrderGoods,Long>,JpaSpecificationExecutor<PhOrderGoods> {

	List<PhOrderGoods> findByOrderNo(String orderNo);
}
