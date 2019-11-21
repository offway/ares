package cn.offway.ares.service.impl;

import cn.offway.ares.domain.PhWardrobeAudit;
import cn.offway.ares.repository.PhWardrobeAuditRepository;
import cn.offway.ares.service.PhWardrobeAuditService;
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
 * 衣柜审核Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-21 17:04:14 Exp $
 */
@Service
public class PhWardrobeAuditServiceImpl implements PhWardrobeAuditService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PhWardrobeAuditRepository phWardrobeAuditRepository;

    @Override
    public PhWardrobeAudit save(PhWardrobeAudit phWardrobeAudit) {
        return phWardrobeAuditRepository.save(phWardrobeAudit);
    }

    @Override
    public PhWardrobeAudit findOne(Long id) {
        return phWardrobeAuditRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        phWardrobeAuditRepository.delete(id);
    }

    @Override
    public Page<PhWardrobeAudit> listAll(String brandId, String goodsName, String goodsId, String state, Pageable pageable) {
        return phWardrobeAuditRepository.findAll(new Specification<PhWardrobeAudit>() {
            @Override
            public Predicate toPredicate(Root<PhWardrobeAudit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> params = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(brandId)) {
                    params.add(cb.equal(root.get("brandId"), brandId));
                }
                if (StringUtils.isNotBlank(goodsName)) {
                    params.add(cb.like(root.get("goodsName"), "%" + goodsName + "%"));
                }
                if (StringUtils.isNotBlank(goodsId)) {
                    params.add(cb.equal(root.get("goodsId"), goodsId));
                }
                if (StringUtils.isNotBlank(state)) {
                    params.add(cb.equal(root.get("state"), state));
                }
                Predicate[] predicates = new Predicate[params.size()];
                query.where(params.toArray(predicates));
                return null;
            }
        }, pageable);
    }

    @Override
    public List<PhWardrobeAudit> save(List<PhWardrobeAudit> entities) {
        return phWardrobeAuditRepository.save(entities);
    }
}
