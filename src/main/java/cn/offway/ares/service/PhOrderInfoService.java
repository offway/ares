package cn.offway.ares.service;

import java.util.List;

import cn.offway.ares.domain.PhOrderInfo;
import cn.offway.ares.utils.JsonResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 订单Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhOrderInfoService {

    PhOrderInfo save(PhOrderInfo phOrderInfo);

    PhOrderInfo findOne(Long id);

    String generateOrderNo(String prefix);

    Page<PhOrderInfo> findByPage(String sku, String isUpload, String realName, String position, String orderNo, String unionid, String status, Long brandId, String isOffway, List<Long> brandIds, String users, String size, Pageable page);

    JsonResult saveOrder(String orderNo);

    PhOrderInfo findByOrderNo(String orderNo);

    void cancel(String orderNo) throws Exception;

}
