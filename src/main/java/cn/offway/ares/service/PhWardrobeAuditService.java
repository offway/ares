package cn.offway.ares.service;


import cn.offway.ares.domain.PhWardrobeAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 衣柜审核Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-21 17:04:14 Exp $
 */
public interface PhWardrobeAuditService {

    PhWardrobeAudit save(PhWardrobeAudit phWardrobeAudit);

    PhWardrobeAudit findOne(Long id);

    void delete(Long id);

    List<PhWardrobeAudit> save(List<PhWardrobeAudit> entities);

    Page<PhWardrobeAudit> listAll(String brandId, String goodsName, String goodsId, String state, List<Long> brandIds, Pageable pageable);
}
