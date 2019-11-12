package cn.offway.ares.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.offway.ares.domain.PhOfflineOrdersGoods;
import cn.offway.ares.repository.PhOfflineOrdersGoodsRepository;
import cn.offway.ares.service.PhOfflineOrdersGoodsService;
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


/**
 * Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-09-27 13:41:55 Exp $
 */
@Service
public class PhOfflineOrdersGoodsServiceImpl implements PhOfflineOrdersGoodsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhOfflineOrdersGoodsRepository phOfflineOrdersGoodsRepository;
	
	@Override
	public PhOfflineOrdersGoods save(PhOfflineOrdersGoods phOfflineOrdersGoods){
		return phOfflineOrdersGoodsRepository.save(phOfflineOrdersGoods);
	}
	
	@Override
	public PhOfflineOrdersGoods findOne(Long id){
		return phOfflineOrdersGoodsRepository.findOne(id);
	}

	@Override
	public void delete(Long id){
		phOfflineOrdersGoodsRepository.delete(id);
	}

	@Override
	public List<PhOfflineOrdersGoods> save(List<PhOfflineOrdersGoods> entities){
		return phOfflineOrdersGoodsRepository.save(entities);
	}

	@Override
	public Page<PhOfflineOrdersGoods> findByPage(String ordersNo, Pageable page){
		return phOfflineOrdersGoodsRepository.findAll(new Specification<PhOfflineOrdersGoods>() {
			@Override
			public Predicate toPredicate(Root<PhOfflineOrdersGoods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> params = new ArrayList<Predicate>();
				if (StringUtils.isNotBlank(ordersNo)){
					params.add(criteriaBuilder.equal(root.get("ordersNo"),ordersNo));
				}
				Predicate[] predicates = new Predicate[params.size()];
				criteriaQuery.where(params.toArray(predicates));
				return null;
			}
		},page);
	}

	@Override
	public List<PhOfflineOrdersGoods> findByordersNo(String ordersNo){
		return phOfflineOrdersGoodsRepository.findByOrdersNo(ordersNo);
	}

	@Override
	public void delbyOrdersNo(String ordersNo){
		phOfflineOrdersGoodsRepository.deleteByOrdersNo(ordersNo);
	}
}
