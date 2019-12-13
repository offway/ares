package cn.offway.ares.service.impl;

import cn.offway.ares.config.AsciiPredicate;
import cn.offway.ares.domain.PhBrand;
import cn.offway.ares.repository.PhBrandRepository;
import cn.offway.ares.service.PhBrandService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 品牌库Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhBrandServiceImpl implements PhBrandService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PhBrandRepository phBrandRepository;

    @Override
    public PhBrand save(PhBrand phBrand) {

        if (null != phBrand.getId()) {
            updateChildren(phBrand.getId(), phBrand.getLogo(), phBrand.getName());
        }
        return phBrandRepository.save(phBrand);
    }

    @Override
    public List<PhBrand> save(List<PhBrand> phBrands) {
        return phBrandRepository.save(phBrands);
    }

    @Override
    public PhBrand findOne(Long id) {
        return phBrandRepository.findOne(id);
    }

    @Override
    public List<PhBrand> findAll() {
        return phBrandRepository.findAll();
    }

    @Override
    public List<PhBrand> findByIds(List<Long> ids) {
        if (null != ids && ids.size() > 0) {
            return phBrandRepository.findByIds(ids);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void updateChildren(Long id, String logo, String name) {
        phBrandRepository.updateGoods(id, logo, name);
        phBrandRepository.updateGoodsStock(id, logo, name);
        phBrandRepository.updateOrderGoods(id, logo, name);
    }

    @Override
    public Page<PhBrand> findByPage(final Long id, final String name, List<Long> brandIds, Pageable page) {
        return phBrandRepository.findAll(new Specification<PhBrand>() {

            @Override
            public Predicate toPredicate(Root<PhBrand> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> params = new ArrayList<Predicate>();

                if (StringUtils.isNotBlank(name)) {
                    params.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                }

                if (null != id) {
                    params.add(criteriaBuilder.equal(root.get("id"), id));
                }

                if (brandIds != null) {
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("id"));
                    for (Object id : brandIds) {
                        in.value(id);
                    }
                    params.add(in);
                }

                Predicate[] predicates = new Predicate[params.size()];
                criteriaQuery.where(params.toArray(predicates));
                return null;
            }
        }, page);
    }

    private <Y extends Comparable<? super Y>> Predicate ascii(CriteriaBuilderImpl criteriaBuilder,
                                                              Expression<? extends Y> expression) {
        return new AsciiPredicate<>(criteriaBuilder, expression, null);
    }

    @Override
    public List<PhBrand> findAll(String prefix) {
        return phBrandRepository.findAll(new Specification<PhBrand>() {
            @Override
            public Predicate toPredicate(Root<PhBrand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> params = new ArrayList<Predicate>();
                if ("#".equals(prefix)) {
                    params.add(ascii((CriteriaBuilderImpl) cb, root.get("name")));
                } else {
                    params.add(cb.like(root.get("name"), prefix + "%"));
                }
                Predicate[] predicates = new Predicate[params.size()];
                query.where(params.toArray(predicates));
                return null;
            }
        });
    }

    @Override
    public List<PhBrand> findByShowImgId(Long showImgId) {
        return phBrandRepository.findByShowImgId(showImgId);
    }

}
