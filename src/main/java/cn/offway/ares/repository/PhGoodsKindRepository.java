package cn.offway.ares.repository;

import cn.offway.ares.domain.PhGoodsKind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品种类Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-18 14:49:58 Exp $
 */
public interface PhGoodsKindRepository extends JpaRepository<PhGoodsKind, Long>, JpaSpecificationExecutor<PhGoodsKind> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM `ph_goods_kind` WHERE (`goods_category` = ?1)")
    void deleteByPid(Long pid);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM `ph_goods_kind` WHERE (`goods_type` = ?1)")
    void deleteByPPid(Long pid);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update `ph_goods_kind` set `sort` = `sort` + 1 where `sort` >= ?1 and `goods_category` = ?2")
    void resort(Long sort, Long theId);
}
