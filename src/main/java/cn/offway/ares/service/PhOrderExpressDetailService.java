package cn.offway.ares.service;

import cn.offway.ares.domain.PhOrderExpressDetail;

/**
 * 订单物流详情Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhOrderExpressDetailService{

	PhOrderExpressDetail save(PhOrderExpressDetail phOrderExpressDetail);
	
	PhOrderExpressDetail findOne(Long id);
}
