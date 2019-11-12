package cn.offway.ares.service;

import java.util.List;

import cn.offway.ares.domain.PhGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 商品表Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhGoodsService {

    PhGoods save(PhGoods phGoods);

    PhGoods findOne(Long id);

    void save(PhGoods phGoods, String banner, String detail);

    List<PhGoods> findAll(List<Long> ids);

    List<PhGoods> save(List<PhGoods> phGoods);

    boolean imagesDelete(Long goodsImageId);

    List<PhGoods> findByBrandId(Long brandId);

    Page<PhGoods> findByPage(String name, Long brandId, String isOffway, List<Long> brandIds, String status, String type, String category, Pageable page);

    String delete(List<Long> goodsIds);

    List<PhGoods> findAll(String name, Object value, String brandId);
}
