package cn.offway.ares.repository;

import cn.offway.ares.domain.PhFeedbackDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 反馈表明细Repository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-09-25 15:10:52 Exp $
 */
public interface PhFeedbackDetailRepository extends JpaRepository<PhFeedbackDetail, Long>, JpaSpecificationExecutor<PhFeedbackDetail> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM `ph_feedback_detail` WHERE (`pid` = ?1)")
    void deleteByPid(Long pid);
}
