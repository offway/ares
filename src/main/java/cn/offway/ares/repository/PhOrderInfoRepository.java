package cn.offway.ares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cn.offway.ares.domain.PhOrderInfo;
import java.lang.String;

/**
 * 订单Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhOrderInfoRepository extends JpaRepository<PhOrderInfo,Long>,JpaSpecificationExecutor<PhOrderInfo> {

	/**
	 * 生成订单号
	 * @return
	 */
	@Query(nativeQuery=true,value="select CONCAT(?1,DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextval(CONCAT(?1,DATE_FORMAT(NOW(),'%Y%m%d'))), 5, 0))")
	String generateOrderNo(String prefix);
	
	@Query(nativeQuery=true,value="select count(*) from sequence where name=CONCAT(?1,DATE_FORMAT(NOW(),'%Y%m%d'))")
	int hasOrder(String prefix);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,value="insert into sequence values(CONCAT(?1,DATE_FORMAT(NOW(),'%Y%m%d')),0,1)")
	int insert(String prefix);
	
	PhOrderInfo findByOrderNo(String orderNo);
}
