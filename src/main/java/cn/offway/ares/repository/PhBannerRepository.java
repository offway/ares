package cn.offway.ares.repository;

import cn.offway.ares.domain.PhBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Banner管理Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhBannerRepository extends JpaRepository<PhBanner, Long>, JpaSpecificationExecutor<PhBanner> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update `ph_banner` set `sort` = `sort` + 1 where `sort` >= ?1 AND `status` = 1")
    void resort(Long sort);

    @Query(nativeQuery = true, value = "SELECT max(sort) as a FROM ph_banner where status = 1")
    Optional<String> getMaxSort();
}
