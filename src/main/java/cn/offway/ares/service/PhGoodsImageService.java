package cn.offway.ares.service;

import cn.offway.ares.domain.PhGoodsImage;

import java.util.List;

/**
 * 商品图片Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhGoodsImageService {

    PhGoodsImage save(PhGoodsImage phGoodsImage);

    PhGoodsImage findOne(Long id);

    List<PhGoodsImage> save(List<PhGoodsImage> phGoodsImages);

    List<PhGoodsImage> findByGoodsId(Long goodsId);

    void delete(PhGoodsImage phGoodsImage);

    void deleteByGoodsIds(List<Long> goodsIds);

    void delete(List<PhGoodsImage> phGoodsImages);

    void del(Long id);

    void delByPid(Long id);

    List<PhGoodsImage> findByPid(Long pid);
}
