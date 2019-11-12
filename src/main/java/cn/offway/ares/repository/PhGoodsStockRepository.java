package cn.offway.ares.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cn.offway.ares.domain.PhGoodsStock;

/**
 * 商品库存Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhGoodsStockRepository extends JpaRepository<PhGoodsStock,Long>,JpaSpecificationExecutor<PhGoodsStock> {

	int countByGoodsIdAndColorAndSize(Long goodsId,String color,String size);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="update ph_goods_stock s set s.stock =  s.stock+1 where  s.goods_id=?1  and s.color =?2 and s.size=?3")
	int updateStock(Long goodsId,String color,String size);
	
	@Query(nativeQuery=true,value="select s.image from ph_goods_stock s where  s.goods_id=?2  and s.color =?1 and s.image is not null  limit 1")
	String findImage(String color,Long goodsId);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="update ph_goods_stock s set s.image = ?3 where  s.goods_id=?1  and s.color =?2 ")
	int updateImage(Long goodsId,String color,String image);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="delete from ph_goods_stock where goods_id in(?1)")
	int deleteByGoodsIds(List<Long> goodsIds);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="delete from ph_goods_stock where id in(?1)")
	int deleteByIds(List<Long> ids);
}
