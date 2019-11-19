package cn.offway.ares.service.impl;

import cn.offway.ares.domain.PhBrand;
import cn.offway.ares.domain.PhGoods;
import cn.offway.ares.domain.PhGoodsImage;
import cn.offway.ares.properties.QiniuProperties;
import cn.offway.ares.repository.PhGoodsRepository;
import cn.offway.ares.repository.PhOrderGoodsRepository;
import cn.offway.ares.repository.PhWardrobeRepository;
import cn.offway.ares.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 商品表Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhGoodsServiceImpl implements PhGoodsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PhGoodsRepository phGoodsRepository;

    @Autowired
    private QiniuService qiniuService;

    @Autowired
    private QiniuProperties qiniuProperties;

    @Autowired
    private PhGoodsImageService phGoodsImageService;

    @Autowired
    private PhBrandService phBrandService;

    @Autowired
    private PhGoodsStockService phGoodsStockService;

    @Autowired
    private PhWardrobeRepository phWardrobeRepository;

    @Autowired
    private PhOrderGoodsRepository phOrderGoodsRepository;

    @Override
    public PhGoods save(PhGoods phGoods) {
        if (null != phGoods.getId()) {
            updateChildren(phGoods.getId(), phGoods.getName());
        }
        return phGoodsRepository.save(phGoods);
    }

    @Override
    public List<PhGoods> save(List<PhGoods> phGoods) {
        return phGoodsRepository.save(phGoods);
    }

    @Override
    public PhGoods findOne(Long id) {
        return phGoodsRepository.findOne(id);
    }

    @Override
    public List<PhGoods> findAll(List<Long> ids) {
        return phGoodsRepository.findAll(ids);
    }

    private void updateChildren(Long id, String name) {
        phGoodsRepository.updateGoodsStock(id, name);
        phGoodsRepository.updateOrderGoods(id, name);
    }

    @Override
    public String delete(List<Long> goodsIds) {

        int c = phGoodsRepository.countByGoodsIds(goodsIds);
        if (c > 0) {
            return "1";
        }
        int count = phOrderGoodsRepository.countByGoodsIds(goodsIds);
        if (count > 0) {
            return "2";
        }
        phWardrobeRepository.deleteByGoodsIds(goodsIds);
        phGoodsImageService.deleteByGoodsIds(goodsIds);
        phGoodsStockService.deleteByGoodsIds(goodsIds);
        phGoodsRepository.deleteByGoodsIds(goodsIds);

        return "0";
    }

    @Override
    public List<PhGoods> findByBrandId(Long brandId) {
        return phGoodsRepository.findByBrandId(brandId);
    }

    @Override
    public Page<PhGoods> findByPage(final String name, final Long brandId, final String isOffway, final List<Long> brandIds, String status, String type, String category, Pageable page) {
        return phGoodsRepository.findAll(new Specification<PhGoods>() {

            @Override
            public Predicate toPredicate(Root<PhGoods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> params = new ArrayList<Predicate>();

                if (StringUtils.isNotBlank(name)) {
                    params.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                }

                if (null != brandId) {
                    params.add(criteriaBuilder.equal(root.get("brandId"), brandId));
                }

                if (StringUtils.isNotBlank(isOffway)) {
                    params.add(criteriaBuilder.equal(root.get("isOffway"), isOffway));
                }

                if (StringUtils.isNotBlank(status)) {
                    params.add(criteriaBuilder.equal(root.get("status"), status));
                }

                if (StringUtils.isNotBlank(type)) {
                    params.add(criteriaBuilder.equal(root.get("type"), type));
                }

                if (StringUtils.isNotBlank(category)) {
                    params.add(criteriaBuilder.equal(root.get("category"), category));
                }

                if (CollectionUtils.isNotEmpty(brandIds)) {
                    In<Object> in = criteriaBuilder.in(root.get("brandId"));
                    for (Object brandId : brandIds) {
                        in.value(brandId);
                    }
                    params.add(in);
                }

                Predicate[] predicates = new Predicate[params.size()];
                criteriaQuery.where(params.toArray(predicates));
                return null;
            }
        }, page);
    }

    @Override
    public void save(PhGoods phGoods, String banner, String detail) {
        phGoods.setCreateTime(new Date());
        Long productId = phGoods.getId();
        if (null != productId) {
            PhGoods goods = findOne(productId);
            String image = goods.getImage();
            if (!image.equals(phGoods.getImage())) {
                //如果资源变动则删除七牛资源
                qiniuService.qiniuDelete(image.replace(qiniuProperties.getUrl() + "/", ""));
            }
            phGoods.setCreateTime(goods.getCreateTime());
            phGoods.setStatus(goods.getStatus());
        }
        PhBrand phBrand = phBrandService.findOne(phGoods.getBrandId());
        phGoods.setBrandLogo(phBrand.getLogo());
        phGoods.setBrandName(phBrand.getName());
        phGoods = save(phGoods);

        List<String> banners = Arrays.asList(banner.split("#"));
        List<String> details = Arrays.asList(detail.split("#"));
        List<PhGoodsImage> goodsImages = phGoodsImageService.findByGoodsId(productId);
        for (PhGoodsImage phGoodsImage : goodsImages) {
            String image = phGoodsImage.getImageUrl();
            if (phGoodsImage.getType().equals("0") && (!banners.contains(image))) {
                //如果资源变动则删除七牛资源
                qiniuService.qiniuDelete(image.replace(qiniuProperties.getUrl() + "/", ""));
            }
            if (phGoodsImage.getType().equals("1") && (!details.contains(image))) {
                //如果资源变动则删除七牛资源
                qiniuService.qiniuDelete(image.replace(qiniuProperties.getUrl() + "/", ""));
            }
        }

        phGoodsImageService.delete(goodsImages);
        List<PhGoodsImage> images = new ArrayList<>();
        Date now = new Date();
        for (String b : banners) {
            if (StringUtils.isNotBlank(b)) {
                PhGoodsImage goodsImage = new PhGoodsImage();
                goodsImage.setGoodsId(phGoods.getId());
                goodsImage.setGoodsName(phGoods.getName());
                goodsImage.setCreateTime(now);
                goodsImage.setImageUrl(b);
                goodsImage.setSort(0L);
                goodsImage.setType("0");
                images.add(goodsImage);
            }
        }

        for (String b : details) {
            if (StringUtils.isNotBlank(b)) {
                PhGoodsImage goodsImage = new PhGoodsImage();
                goodsImage.setGoodsId(phGoods.getId());
                goodsImage.setGoodsName(phGoods.getName());
                goodsImage.setCreateTime(now);
                goodsImage.setImageUrl(b);
                goodsImage.setSort(0L);
                goodsImage.setType("1");
                images.add(goodsImage);
            }
        }
        phGoodsImageService.save(images);


    }

    @Override
    public List<PhGoods> findAll(String name, Object value, String brandId) {
        return phGoodsRepository.findAll(new Specification<PhGoods>() {
            @Override
            public Predicate toPredicate(Root<PhGoods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> params = new ArrayList<Predicate>();
//				params.add(cb.equal(root.get("status"), "1"));
                if ("keyword".equals(name)) {
                    Predicate like = cb.like(root.get("name"), "%" + value + "%");
                    if (StringUtils.isNumeric(String.valueOf(value))) {
                        Predicate or = cb.equal(root.get("id"), value);
                        params.add(cb.or(like, or));
                    } else {
                        params.add(like);
                    }
                } else {
                    params.add(cb.equal(root.get(name), value));
                }
                if (StringUtils.isNotBlank(brandId)) {
                    params.add(cb.equal(root.get("brandId"), brandId));
                }
                Predicate[] predicates = new Predicate[params.size()];
                query.where(params.toArray(predicates));
                return null;
            }
        });
    }

    @Override
    public Page<PhGoods> findAll(String name, Long id, String status, String type, String category, String kind, Pageable pageable) {
        return phGoodsRepository.findAll(new Specification<PhGoods>() {
            @Override
            public Predicate toPredicate(Root<PhGoods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> params = new ArrayList<Predicate>();
                if (StringUtils.isNotBlank(name)) {
                    params.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                }
                if (StringUtils.isNotBlank(status)) {
                    params.add(criteriaBuilder.equal(root.get("status"), status));
                }
                if (id != 0L) {
                    params.add(criteriaBuilder.equal(root.get("id"), id));
                }
                if (StringUtils.isNotBlank(type)) {
                    params.add(criteriaBuilder.equal(root.get("type"), type));
                }
                if (StringUtils.isNotBlank(category)) {
                    params.add(criteriaBuilder.equal(root.get("category"), category));
                }
                if (StringUtils.isNotBlank(kind)) {
                    params.add(criteriaBuilder.equal(root.get("categoryDetails"), kind));
                }
                Predicate[] predicates = new Predicate[params.size()];
                criteriaQuery.where(params.toArray(predicates));
                return null;
            }
        }, pageable);
    }

    @Override
    public void del(Long id) {
        phGoodsRepository.delete(id);
    }

    @Override
    public boolean imagesDelete(Long goodsImageId) {
        PhGoodsImage goodsImage = phGoodsImageService.findOne(goodsImageId);
        String image = goodsImage.getImageUrl();
        qiniuService.qiniuDelete(image.replace(qiniuProperties.getUrl() + "/", ""));
        phGoodsImageService.delete(goodsImage);
        return true;
    }
}
