package cn.offway.ares.service.impl;

import cn.offway.ares.domain.PhBanner;
import cn.offway.ares.repository.PhBannerRepository;
import cn.offway.ares.service.PhBannerService;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Optional;

/**
 * Banner管理Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhBannerServiceImpl implements PhBannerService {
    @Autowired
    private PhBannerRepository bannerRepository;

    @Override
    public PhBanner save(PhBanner banner) {
        return bannerRepository.save(banner);
    }

    @Override
    public PhBanner findOne(Long id) {
        return bannerRepository.findOne(id);
    }

    @Override
    public Page<PhBanner> findAll(Pageable pageable) {
        return bannerRepository.findAll(pageable);
    }

    @Override
    public Long getMaxSort() {
        Optional<String> res = bannerRepository.getMaxSort();
        if (res.isPresent()) {
            return Long.valueOf(String.valueOf(res.get()));
        } else {
            return 0L;
        }
    }

    @Override
    public void resort(Long sort) {
        bannerRepository.resort(sort);
    }

    @Override
    public void delete(Long id) {
        bannerRepository.delete(id);
    }

    @Override
    public Page<PhBanner> findAll(String id, String remark, Pageable pageable) {
        return bannerRepository.findAll(new Specification<PhBanner>() {
            @Override
            public Predicate toPredicate(Root<PhBanner> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> params = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(id)) {
                    params.add(criteriaBuilder.equal(root.get("id"), id));
                }
                if (StringUtils.isNotBlank(remark)) {
                    params.add(criteriaBuilder.like(root.get("remark"), "%" + remark + "%"));
                }
                Predicate[] predicates = new Predicate[params.size()];
                criteriaQuery.where(params.toArray(predicates));
                return null;
            }
        }, pageable);
    }
}
