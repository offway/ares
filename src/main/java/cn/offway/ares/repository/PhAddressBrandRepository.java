package cn.offway.ares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.offway.ares.domain.PhAddressBrand;

/**
 * 品牌地址管理Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-27 18:02:40 Exp $
 */
public interface PhAddressBrandRepository extends JpaRepository<PhAddressBrand,Long>,JpaSpecificationExecutor<PhAddressBrand> {

	/** 此处写一些自定义的方法 **/
}
