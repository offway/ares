package cn.offway.ares.service;


import java.util.List;

import cn.offway.ares.domain.PhGoodsProperty;

/**
 * 商品属性Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-19 11:38:30 Exp $
 */
public interface PhGoodsPropertyService{

    PhGoodsProperty save(PhGoodsProperty phGoodsProperty);
	
    PhGoodsProperty findOne(Long id);

    void delete(Long id);

    List<PhGoodsProperty> save(List<PhGoodsProperty> entities);
}
