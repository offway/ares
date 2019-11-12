package cn.offway.ares.service;

import cn.offway.ares.domain.PhBanner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Banner管理Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhBannerService {
    PhBanner save(PhBanner phBanner);

    PhBanner findOne(Long id);

    Page<PhBanner> findAll(Pageable pageable);

    void delete(Long id);

    void resort(Long sort);

    Long getMaxSort();

    Page<PhBanner> findAll(String id, String remark, Pageable pageable);
}
