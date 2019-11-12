package cn.offway.ares.repository;

import cn.offway.ares.domain.VRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * VIEWRepository接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-09-05 12:57:03 Exp $
 */
public interface VRankingRepository extends JpaRepository<VRanking,Long>,JpaSpecificationExecutor<VRanking> {

	/** 此处写一些自定义的方法 **/
}
