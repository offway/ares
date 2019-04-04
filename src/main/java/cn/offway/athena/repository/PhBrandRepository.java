package cn.offway.athena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.offway.athena.domain.PhBrand;
import java.lang.Long;
import java.util.List;

/**
 * 品牌库Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhBrandRepository extends JpaRepository<PhBrand,Long>,JpaSpecificationExecutor<PhBrand> {

	@Query(nativeQuery = true,value="select * from ph_brand where id in(?1)")
	List<PhBrand> findByIds(List<Long> ids);
	
	@Query(nativeQuery = true,value="select * from ph_brand where id in (select brand_id from ph_goods where id in(select goods_id from ph_order_goods where order_no=(select order_no from ph_show_image where id=?1)))")
	List<PhBrand> findByShowImgId(Long showImgId);
}
