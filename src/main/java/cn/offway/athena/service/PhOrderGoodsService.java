package cn.offway.athena.service;

import cn.offway.athena.domain.PhOrderGoods;

/**
 * 订单商品Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhOrderGoodsService{

	PhOrderGoods save(PhOrderGoods phOrderGoods);
	
	PhOrderGoods findOne(Long id);
}
