package cn.offway.ares.service;


import java.util.List;

import cn.offway.ares.domain.PhOrderRemark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-10-08 13:56:14 Exp $
 */
public interface PhOrderRemarkService{

    PhOrderRemark save(PhOrderRemark phOrderRemark);
	
    PhOrderRemark findOne(Long id);

    void delete(Long id);

    List<PhOrderRemark> save(List<PhOrderRemark> entities);

    List<PhOrderRemark> findAllByOrdersId(String id);

    Page<PhOrderRemark> findAllByPage(String id, Pageable page);
}
