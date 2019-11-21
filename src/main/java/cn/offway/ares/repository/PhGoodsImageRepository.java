package cn.offway.ares.repository;

import cn.offway.ares.domain.PhGoodsImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品图片Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhGoodsImageRepository extends JpaRepository<PhGoodsImage, Long>, JpaSpecificationExecutor<PhGoodsImage> {

    @Query(nativeQuery = true, value = "select * from ph_goods_image where goods_id=?1 order by image_url")
    List<PhGoodsImage> findByGoodsId(Long goodsId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from ph_goods_image where goods_id in(?1)")
    void deleteByGoodsIds(List<Long> goodsIds);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM `ph_goods_image` WHERE (`goods_id` = ?1)")
    void deleteByPid(Long pid);
}
