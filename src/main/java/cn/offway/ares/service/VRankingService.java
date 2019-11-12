package cn.offway.ares.service;


import cn.offway.ares.domain.VRanking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * VIEWService接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-09-05 12:57:03 Exp $
 */
public interface VRankingService {

    VRanking save(VRanking vRanking);

    VRanking findOne(Long id);

    void delete(Long id);

    List<VRanking> save(List<VRanking> entities);

    Page<VRanking> findAll(Pageable pageable, String brandId);
}
