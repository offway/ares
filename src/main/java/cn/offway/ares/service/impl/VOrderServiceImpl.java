package cn.offway.ares.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.offway.ares.domain.VOrder;
import cn.offway.ares.repository.VOrderRepository;
import cn.offway.ares.service.VOrderService;


/**
 * VIEWService接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class VOrderServiceImpl implements VOrderService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VOrderRepository vOrderRepository;
	
	@Override
	public VOrder save(VOrder vOrder){
		return vOrderRepository.save(vOrder);
	}
	
	@Override
	public VOrder findOne(Long id){
		return vOrderRepository.findOne(id);
	}
	
	@Override
	public VOrder findByOrderNo(String orderNo){
		return vOrderRepository.findByOrderNo(orderNo);
	}
 
	
	@Override
	public Page<VOrder> findByPage(final String realName,final String position,final String orderNo,final String unionid,final Long brandId,final String isOffway,final List<Long> brandIds,Pageable page){
		return vOrderRepository.findAll(new Specification<VOrder>() {
			
			@Override
			public Predicate toPredicate(Root<VOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> params = new ArrayList<Predicate>();
				
				if(StringUtils.isNotBlank(orderNo)){
					params.add(criteriaBuilder.equal(root.get("orderNo"), orderNo));
				}
				
				if(StringUtils.isNotBlank(realName)){
					params.add(criteriaBuilder.like(root.get("realName"), "%"+realName+"%"));
				}
				
				if(StringUtils.isNotBlank(position)){
					params.add(criteriaBuilder.like(root.get("position"), "%"+position+"%"));
				}
				
				if(StringUtils.isNotBlank(unionid)){
					params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
				}
				
				if(null != brandId){
					params.add(criteriaBuilder.equal(root.get("brandId"), brandId));
				}
				
				if(StringUtils.isNotBlank(isOffway)){
					params.add(criteriaBuilder.equal(root.get("isOffway"), isOffway));
				}
				
				if(CollectionUtils.isNotEmpty(brandIds)){
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
}
