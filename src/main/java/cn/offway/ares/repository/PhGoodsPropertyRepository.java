package cn.offway.ares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.offway.ares.domain.PhGoodsProperty;

/**
 * 商品属性Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-19 11:38:30 Exp $
 */
public interface PhGoodsPropertyRepository extends JpaRepository<PhGoodsProperty,Long>,JpaSpecificationExecutor<PhGoodsProperty> {

	/** 此处写一些自定义的方法 **/
}
