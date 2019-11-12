package cn.offway.ares.service;

import java.util.List;

import cn.offway.ares.domain.PhGoodsImage;

/**
 * 商品图片Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhGoodsImageService{

	PhGoodsImage save(PhGoodsImage phGoodsImage);
	
	PhGoodsImage findOne(Long id);

	List<PhGoodsImage> save(List<PhGoodsImage> phGoodsImages);

	List<PhGoodsImage> findByGoodsId(Long goodsId);

	void delete(PhGoodsImage phGoodsImage);

	int deleteByGoodsIds(List<Long> goodsIds);

	void delete(List<PhGoodsImage> phGoodsImages);
}
