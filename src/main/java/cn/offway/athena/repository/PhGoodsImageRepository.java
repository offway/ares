package cn.offway.athena.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.offway.athena.domain.PhGoodsImage;

/**
 * 商品图片Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhGoodsImageRepository extends JpaRepository<PhGoodsImage,Long>,JpaSpecificationExecutor<PhGoodsImage> {

	@Query(nativeQuery=true,value="select * from ph_goods_image where goods_id=?1 order by sort")
	List<PhGoodsImage> findByGoodsId(Long goodsId);
}
