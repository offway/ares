package cn.offway.ares.service;

import cn.offway.ares.domain.PhCreditDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 信用明细Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhCreditDetailService{

	PhCreditDetail save(PhCreditDetail phCreditDetail);
	
	PhCreditDetail findOne(Long id);

	Page<PhCreditDetail> findByPage(String orderNo, String unionid, String type, Pageable page);
}
