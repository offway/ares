package cn.offway.ares.service.impl;

import cn.offway.ares.domain.PhGoodsImage;
import cn.offway.ares.repository.PhGoodsImageRepository;
import cn.offway.ares.service.PhGoodsImageService;
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
 * 商品图片Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhGoodsImageServiceImpl implements PhGoodsImageService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PhGoodsImageRepository phGoodsImageRepository;

    @Override
    public PhGoodsImage save(PhGoodsImage phGoodsImage) {
        return phGoodsImageRepository.save(phGoodsImage);
    }

    @Override
    public PhGoodsImage findOne(Long id) {
        return phGoodsImageRepository.findOne(id);
    }

    @Override
    public void delete(PhGoodsImage phGoodsImage) {
        phGoodsImageRepository.delete(phGoodsImage);
    }

    @Override
    public void delete(List<PhGoodsImage> phGoodsImages) {
        phGoodsImageRepository.delete(phGoodsImages);
    }

    @Override
    public void deleteByGoodsIds(List<Long> goodsIds) {
        phGoodsImageRepository.deleteByGoodsIds(goodsIds);
    }

    @Override
    public void del(Long id) {
        phGoodsImageRepository.delete(id);
    }

    @Override
    public void delByPid(Long id) {
        phGoodsImageRepository.deleteByPid(id);
    }

    @Override
    public List<PhGoodsImage> findByPid(Long pid) {
        return phGoodsImageRepository.findAll(new Specification<PhGoodsImage>() {
            @Override
            public Predicate toPredicate(Root<PhGoodsImage> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> params = new ArrayList<Predicate>();
                params.add(criteriaBuilder.equal(root.get("goodsId"), pid));
                Predicate[] predicates = new Predicate[params.size()];
                criteriaQuery.where(params.toArray(predicates));
                return null;
            }
        });
    }

    @Override
    public List<PhGoodsImage> save(List<PhGoodsImage> phGoodsImages) {
        return phGoodsImageRepository.save(phGoodsImages);
    }

    @Override
    public List<PhGoodsImage> findByGoodsId(Long goodsId) {
        return phGoodsImageRepository.findByGoodsId(goodsId);
    }

}
