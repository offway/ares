package cn.offway.athena.service;

import cn.offway.athena.domain.PhGoodsStock;

/**
 * 商品库存Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhGoodsStockService{

	PhGoodsStock save(PhGoodsStock phGoodsStock);
	
	PhGoodsStock findOne(Long id);
}
