package cn.offway.ares.service.impl;

import cn.offway.ares.domain.PhGoodsKind;
import cn.offway.ares.repository.PhGoodsKindRepository;
import cn.offway.ares.service.PhGoodsKindService;
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
 * 商品种类Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-18 14:49:58 Exp $
 */
@Service
public class PhGoodsKindServiceImpl implements PhGoodsKindService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PhGoodsKindRepository phGoodsKindRepository;

    @Override
    public PhGoodsKind save(PhGoodsKind phGoodsKind) {
        return phGoodsKindRepository.save(phGoodsKind);
    }

    @Override
    public PhGoodsKind findOne(Long id) {
        return phGoodsKindRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        phGoodsKindRepository.delete(id);
    }

    private Specification<PhGoodsKind> getSpec(Object pid) {
        return new Specification<PhGoodsKind>() {
            /**
             * Creates a WHERE clause for a query of the referenced entity in form of a {@link Predicate} for the given
             * {@link Root} and {@link CriteriaQuery}.
             *
             * @param root
             * @param query
             * @param cb
             * @return a {@link Predicate}, may be {@literal null}.
             */
            @Override
            public Predicate toPredicate(Root<PhGoodsKind> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> params = new ArrayList<Predicate>();
                params.add(cb.equal(root.get("goodsCategory"), pid));
                Predicate[] predicates = new Predicate[params.size()];
                query.where(params.toArray(predicates));
                return null;
            }
        };
    }

    @Override
    public List<PhGoodsKind> findByPid(Long pid) {
        return phGoodsKindRepository.findAll(getSpec(pid));
    }

    @Override
    public Page<PhGoodsKind> findByPid(Long pid, Pageable pageable) {
        return phGoodsKindRepository.findAll(getSpec(pid), pageable);
    }

    @Override
    public void delByPid(Long pid) {
        phGoodsKindRepository.deleteByPid(pid);
    }

    @Override
    public void delByPPid(Long ppid) {
        phGoodsKindRepository.deleteByPPid(ppid);
    }

    @Override
    public void resort(Long sort, Long theId) {
        phGoodsKindRepository.resort(sort, theId);
    }

    @Override
    public List<PhGoodsKind> save(List<PhGoodsKind> entities) {
        return phGoodsKindRepository.save(entities);
    }
}
