package cn.offway.ares.service;


import cn.offway.ares.domain.PhFeedbackDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * 反馈表明细Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-09-25 15:10:52 Exp $
 */
public interface PhFeedbackDetailService {

    PhFeedbackDetail save(PhFeedbackDetail phFeedbackDetail);

    PhFeedbackDetail findOne(Long id);

    Long checkStarName(Long pid, String starName);

    void delete(Long id);

    List<PhFeedbackDetail> save(List<PhFeedbackDetail> entities);

    Page<PhFeedbackDetail> findByPid(Long pid, String starName, Date sTime, Date eTime, Pageable pageable);

    void delByPid(Long pid);
}
