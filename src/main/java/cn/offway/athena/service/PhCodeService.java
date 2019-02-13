package cn.offway.athena.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.offway.athena.domain.PhCode;

/**
 * 邀请码Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhCodeService{

	PhCode save(PhCode phCode);
	
	PhCode findOne(Long id);

	Page<PhCode> findByPage(String status, String code, String phone, Pageable page);
}
