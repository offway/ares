package cn.offway.ares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.offway.ares.domain.PhGoodsKind;

/**
 * 商品种类Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-18 14:49:58 Exp $
 */
public interface PhGoodsKindRepository extends JpaRepository<PhGoodsKind,Long>,JpaSpecificationExecutor<PhGoodsKind> {

	/** 此处写一些自定义的方法 **/
}
