package cn.offway.ares.service.impl;

import cn.offway.ares.domain.PhGoodsProperty;
import cn.offway.ares.repository.PhGoodsPropertyRepository;
import cn.offway.ares.service.PhGoodsPropertyService;
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
 * 商品属性Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-19 11:38:30 Exp $
 */
@Service
public class PhGoodsPropertyServiceImpl implements PhGoodsPropertyService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PhGoodsPropertyRepository phGoodsPropertyRepository;

    @Override
    public PhGoodsProperty save(PhGoodsProperty phGoodsProperty) {
        return phGoodsPropertyRepository.save(phGoodsProperty);
    }

    @Override
    public PhGoodsProperty findOne(Long id) {
        return phGoodsPropertyRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        phGoodsPropertyRepository.delete(id);
    }

    private Specification<PhGoodsProperty> getFilter(String name, Object value) {
        return new Specification<PhGoodsProperty>() {
            @Override
            public Predicate toPredicate(Root<PhGoodsProperty> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> params = new ArrayList<>();
                params.add(criteriaBuilder.equal(root.get(name), value));
                Predicate[] predicates = new Predicate[params.size()];
                criteriaQuery.where(params.toArray(predicates));
                return null;
            }
        };
    }

    @Override
    public void delByPid(Long id) {
        phGoodsPropertyRepository.deleteByPid(id);
    }

    @Override
    public void delByStockId(Long sid) {
        phGoodsPropertyRepository.deleteByStockId(sid);
    }

    @Override
    public List<PhGoodsProperty> findByPid(Long pid) {
        return phGoodsPropertyRepository.findAll(getFilter("goodsId", pid));
    }

    @Override
    public List<PhGoodsProperty> findByStockId(Long sid) {
        return phGoodsPropertyRepository.findAll(getFilter("goodsStockId", sid));
    }

    @Override
    public List<PhGoodsProperty> save(List<PhGoodsProperty> entities) {
        return phGoodsPropertyRepository.save(entities);
    }
}
