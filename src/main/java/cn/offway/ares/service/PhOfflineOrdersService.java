package cn.offway.ares.service;


import java.util.Date;
import java.util.List;

import cn.offway.ares.domain.PhOfflineOrders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-09-27 13:41:55 Exp $
 */
public interface PhOfflineOrdersService {

    PhOfflineOrders save(PhOfflineOrders phOfflineOrders);

    PhOfflineOrders findOne(Long id);

    void delete(Long id);

    List<PhOfflineOrders> save(List<PhOfflineOrders> entities);

    Page<PhOfflineOrders> findByPage(String realName, String users, String state, String ordersNo, Date sTime, Date eTime, Pageable page);
}
