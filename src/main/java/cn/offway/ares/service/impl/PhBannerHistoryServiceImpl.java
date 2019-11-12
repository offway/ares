package cn.offway.ares.service.impl;

import cn.offway.ares.domain.PhBannerHistory;
import cn.offway.ares.repository.PhBannerHistoryRepository;
import cn.offway.ares.service.PhBannerHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 * Banner 历史Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-10-10 14:00:40 Exp $
 */
@Service
public class PhBannerHistoryServiceImpl implements PhBannerHistoryService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PhBannerHistoryRepository phBannerHistoryRepository;

    @Override
    public PhBannerHistory save(PhBannerHistory phBannerHistory) {
        return phBannerHistoryRepository.save(phBannerHistory);
    }

    @Override
    public List<PhBannerHistory> findList(String id) {
        return phBannerHistoryRepository.findAll(new Specification<PhBannerHistory>() {
            @Override
            public Predicate toPredicate(Root<PhBannerHistory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> params = new ArrayList<Predicate>();
                params.add(cb.equal(root.get("bannerId"), id));
                Predicate[] predicates = new Predicate[params.size()];
                query.where(params.toArray(predicates));
                return null;
            }
        });
    }

    @Override
    public PhBannerHistory findOne(Long id) {
        return phBannerHistoryRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        phBannerHistoryRepository.delete(id);
    }

    @Override
    public List<PhBannerHistory> save(List<PhBannerHistory> entities) {
        return phBannerHistoryRepository.save(entities);
    }
}
