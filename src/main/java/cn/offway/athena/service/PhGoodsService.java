package cn.offway.athena.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.offway.athena.domain.PhGoods;

/**
 * 商品表Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhGoodsService{

	PhGoods save(PhGoods phGoods);
	
	PhGoods findOne(Long id);

	Page<PhGoods> findByPage(String name, Pageable page);

	void save(PhGoods phGoods, String banner, String detail);

	List<PhGoods> findAll(List<Long> ids);

	List<PhGoods> save(List<PhGoods> phGoods);

	boolean imagesDelete(Long goodsImageId);

	List<PhGoods> findByBrandId(Long brandId);
}
