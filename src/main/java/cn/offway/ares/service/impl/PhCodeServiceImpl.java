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

import cn.offway.ares.domain.PhCode;
import cn.offway.ares.repository.PhCodeRepository;
import cn.offway.ares.service.PhCodeService;


/**
 * 邀请码Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhCodeServiceImpl implements PhCodeService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhCodeRepository phCodeRepository;
	
	@Override
	public PhCode save(PhCode phCode){
		return phCodeRepository.save(phCode);
	}
	
	@Override
	public PhCode findOne(Long id){
		return phCodeRepository.findOne(id);
	}
	
	@Override
	public PhCode findByCodeAndStatusAndPhoneAndPositionAndRealName(String code,String status,String phone,String position,String realName){
		return phCodeRepository.findByCodeAndStatusAndPhoneAndPositionAndRealName(code, status, phone, position, realName);
	}
	
	@Override
	public Page<PhCode> findByPage(final String status,final String code,final String phone,Pageable page){
		return phCodeRepository.findAll(new Specification<PhCode>() {
			
			@Override
			public Predicate toPredicate(Root<PhCode> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> params = new ArrayList<Predicate>();
				
				if(StringUtils.isNotBlank(status)){
					params.add(criteriaBuilder.equal(root.get("status"), status));
				}
				
				if(StringUtils.isNotBlank(code)){
					params.add(criteriaBuilder.equal(root.get("code"), code));
				}
				
				if(StringUtils.isNotBlank(phone)){
					params.add(criteriaBuilder.equal(root.get("phone"), phone));
				}
				
                Predicate[] predicates = new Predicate[params.size()];
                criteriaQuery.where(params.toArray(predicates));
				return null;
			}
		}, page);
	}

	@Override
	public void coddel(Long id){
		phCodeRepository.delete(id);
	}
}
