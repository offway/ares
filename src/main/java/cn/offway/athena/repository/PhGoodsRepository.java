package cn.offway.athena.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cn.offway.athena.domain.PhGoods;

/**
 * 商品表Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhGoodsRepository extends JpaRepository<PhGoods,Long>,JpaSpecificationExecutor<PhGoods> {

	List<PhGoods> findByBrandId(Long brandId);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="delete from ph_goods where id in(?1)")
	int deleteByGoodsIds(List<Long> goodsIds);
	
	@Query(nativeQuery=true,value="select count(*) from ph_goods where id in(?1) and status='1'")
	int countByGoodsIds(List<Long> goodsIds);
}
