package cn.offway.ares.service;


import java.util.List;

import cn.offway.ares.domain.PhOfflineOrdersGoods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-09-27 13:41:55 Exp $
 */
public interface PhOfflineOrdersGoodsService{

    PhOfflineOrdersGoods save(PhOfflineOrdersGoods phOfflineOrdersGoods);
	
    PhOfflineOrdersGoods findOne(Long id);

    void delete(Long id);

    List<PhOfflineOrdersGoods> save(List<PhOfflineOrdersGoods> entities);

    Page<PhOfflineOrdersGoods> findByPage(String ordersNo, Pageable page);

    List<PhOfflineOrdersGoods> findByordersNo(String ordersNo);

    void delbyOrdersNo(String ordersNo);
}
