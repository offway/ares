package cn.offway.ares.service;


import java.util.List;

import cn.offway.ares.domain.PhGoodsKind;

/**
 * 商品种类Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-18 14:49:58 Exp $
 */
public interface PhGoodsKindService{

    PhGoodsKind save(PhGoodsKind phGoodsKind);
	
    PhGoodsKind findOne(Long id);

    void delete(Long id);

    List<PhGoodsKind> save(List<PhGoodsKind> entities);
}
