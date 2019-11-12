package cn.offway.ares.service.impl;

import cn.offway.ares.domain.VRanking;
import cn.offway.ares.repository.VRankingRepository;
import cn.offway.ares.service.VRankingService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 * VIEWService接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-09-05 12:57:03 Exp $
 */
@Service
public class VRankingServiceImpl implements VRankingService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VRankingRepository vRankingRepository;

    @Override
    public VRanking save(VRanking vRanking) {
        return vRankingRepository.save(vRanking);
    }

    @Override
    public VRanking findOne(Long id) {
        return vRankingRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        vRankingRepository.delete(id);
    }

    @Override
    public List<VRanking> save(List<VRanking> entities) {
        return vRankingRepository.save(entities);
    }

    @Override
    public Page<VRanking> findAll(Pageable pageable, String brandId) {
        return vRankingRepository.findAll(new Specification<VRanking>() {
            @Override
            public Predicate toPredicate(Root<VRanking> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> params = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(brandId)) {
                    params.add(cb.equal(root.get("brandId"), brandId));
                }
                Predicate[] predicates = new Predicate[params.size()];
                query.where(params.toArray(predicates));
                return null;
            }
        }, pageable);
    }
}
