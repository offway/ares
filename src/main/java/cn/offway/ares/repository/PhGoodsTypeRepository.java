package cn.offway.ares.repository;

import cn.offway.ares.domain.PhGoodsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 商品类别Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-04-04 15:18:00 Exp $
 */
public interface PhGoodsTypeRepository extends JpaRepository<PhGoodsType, Long>, JpaSpecificationExecutor<PhGoodsType> {
}