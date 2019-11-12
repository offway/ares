package cn.offway.ares.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cn.offway.ares.domain.PhWardrobe;

/**
 * 衣柜Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhWardrobeRepository extends JpaRepository<PhWardrobe,Long>,JpaSpecificationExecutor<PhWardrobe> {

	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="delete from ph_wardrobe where goods_id in(?1)")
	int deleteByGoodsIds(List<Long> goodsIds);
}
