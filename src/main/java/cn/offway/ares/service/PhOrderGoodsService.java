package cn.offway.ares.service;

import cn.offway.ares.domain.PhOrderGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单商品Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhOrderGoodsService {

    PhOrderGoods save(PhOrderGoods phOrderGoods);

    PhOrderGoods findOne(Long id);

    List<PhOrderGoods> findByOrderNo(String orderNo, String batch);

    List<PhOrderGoods> findByOrderNo(String orderNo);

    Page<PhOrderGoods> findByBrandId(String brandId, Pageable page);

    int getMaxBatch(String oid);

    int getRest(String oid);
}
