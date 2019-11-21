package cn.offway.ares.service;


import cn.offway.ares.domain.PhGoodsProperty;

import java.util.List;

/**
 * 商品属性Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-19 11:38:30 Exp $
 */
public interface PhGoodsPropertyService {

    PhGoodsProperty save(PhGoodsProperty phGoodsProperty);

    PhGoodsProperty findOne(Long id);

    void delete(Long id);

    List<PhGoodsProperty> save(List<PhGoodsProperty> entities);

    void delByPid(Long id);

    void delByStockId(Long sid);

    List<PhGoodsProperty> findByPid(Long pid);

    List<PhGoodsProperty> findByStockId(Long sid);
}
