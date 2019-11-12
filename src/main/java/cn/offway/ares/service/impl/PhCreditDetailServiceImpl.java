package cn.offway.ares.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import cn.offway.ares.service.PhCreditDetailService;

import cn.offway.ares.domain.PhCreditDetail;
import cn.offway.ares.repository.PhCreditDetailRepository;


/**
 * 信用明细Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhCreditDetailServiceImpl implements PhCreditDetailService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhCreditDetailRepository phCreditDetailRepository;
	
	@Override
	public PhCreditDetail save(PhCreditDetail phCreditDetail){
		return phCreditDetailRepository.save(phCreditDetail);
	}
	
	@Override
	public PhCreditDetail findOne(Long id){
		return phCreditDetailRepository.findOne(id);
	}
	
	@Override
	public Page<PhCreditDetail> findByPage(final String orderNo,final String unionid,final String type,Pageable page){
		return phCreditDetailRepository.findAll(new Specification<PhCreditDetail>() {
			
			@Override
			public Predicate toPredicate(Root<PhCreditDetail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> params = new ArrayList<Predicate>();
				
				if(StringUtils.isNotBlank(orderNo)){
					params.add(criteriaBuilder.equal(root.get("orderNo"), orderNo));
				}
				
				if(StringUtils.isNotBlank(unionid)){
					params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
				}
				
				if(StringUtils.isNotBlank(type)){
					params.add(criteriaBuilder.equal(root.get("type"), type));
				}
				
				
                Predicate[] predicates = new Predicate[params.size()];
                criteriaQuery.where(params.toArray(predicates));
				return null;
			}
		}, page);
	}
}
