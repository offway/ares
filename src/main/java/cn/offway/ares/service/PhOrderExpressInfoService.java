package cn.offway.ares.service;

import cn.offway.ares.domain.PhOrderExpressInfo;

/**
 * 订单物流Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhOrderExpressInfoService{

	PhOrderExpressInfo save(PhOrderExpressInfo phOrderExpressInfo);
	
	PhOrderExpressInfo findOne(Long id);

	PhOrderExpressInfo findByOrderNoAndType(String orderNo, String type);

	PhOrderExpressInfo findByExpressOrderNo(String expressOrderNo);

	PhOrderExpressInfo findByMailNo(String mailNo);
}
