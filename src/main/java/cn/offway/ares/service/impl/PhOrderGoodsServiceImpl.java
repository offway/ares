package cn.offway.ares.service.impl;

import cn.offway.ares.domain.PhOrderGoods;
import cn.offway.ares.repository.PhOrderGoodsRepository;
import cn.offway.ares.service.PhOrderGoodsService;
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
 * 订单商品Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhOrderGoodsServiceImpl implements PhOrderGoodsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PhOrderGoodsRepository phOrderGoodsRepository;

    @Override
    public PhOrderGoods save(PhOrderGoods phOrderGoods) {
        return phOrderGoodsRepository.save(phOrderGoods);
    }

    @Override
    public PhOrderGoods findOne(Long id) {
        return phOrderGoodsRepository.findOne(id);
    }

    @Override
    public List<PhOrderGoods> findByOrderNo(String orderNo) {
        return phOrderGoodsRepository.findByOrderNoOrderByBrandId(orderNo);
    }

    @Override
    public int getMaxBatch(String oid) {
        Object o = phOrderGoodsRepository.getMaxBatch(oid);
        if (o != null) {
            return Integer.valueOf(String.valueOf(o));
        } else {
            return 0;
        }
    }

    @Override
    public Page<PhOrderGoods> findByBrandId(final Long brandId, Pageable page) {
        return phOrderGoodsRepository.findAll(new Specification<PhOrderGoods>() {

            @Override
            public Predicate toPredicate(Root<PhOrderGoods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> params = new ArrayList<Predicate>();

                if (!"".equals(brandId)) {
                    params.add(criteriaBuilder.equal(root.get("brandId"), brandId));
                }

                Predicate[] predicates = new Predicate[params.size()];
                criteriaQuery.where(params.toArray(predicates));

                return null;
            }
        }, page);
    }
}
