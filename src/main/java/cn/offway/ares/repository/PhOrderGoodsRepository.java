package cn.offway.ares.repository;

import cn.offway.ares.domain.PhOrderGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单商品Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhOrderGoodsRepository extends JpaRepository<PhOrderGoods, Long>, JpaSpecificationExecutor<PhOrderGoods> {

    List<PhOrderGoods> findByOrderNoOrderByBrandId(String orderNo);

    List<PhOrderGoods> findByOrderNoAndBatch(String orderNo, Long batch);

    @Query(nativeQuery = true, value = "select count(*) from ph_order_goods where goods_id in(?1)")
    int countByGoodsIds(List<Long> goodsIds);

    @Query(nativeQuery = true, value = "select max(batch) from ph_order_goods where order_no = ?1")
    Object getMaxBatch(String order_no);

    @Query(nativeQuery = true, value = "select count(id) as ct from ph_order_goods where mail_no is null and order_no = ?1")
    Object getRest(String order_no);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update ph_goods_stock gs set gs.stock = gs.stock +1 where EXISTS (select 1 from ph_order_goods og where og.order_no=?1 and og.goods_id = gs.goods_id and og.size = gs.size and og.color = gs.color)")
    int updateStock(String orderNo);
}
