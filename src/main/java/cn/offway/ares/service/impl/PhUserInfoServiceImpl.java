package cn.offway.ares.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.offway.ares.domain.PhUserInfo;
import cn.offway.ares.repository.PhUserInfoRepository;
import cn.offway.ares.service.PhUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


/**
 * 用户信息Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhUserInfoServiceImpl implements PhUserInfoService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhUserInfoRepository phUserInfoRepository;
	
	@Override
	public PhUserInfo save(PhUserInfo phUserInfo){
		return phUserInfoRepository.save(phUserInfo);
	}
	
	@Override
	public PhUserInfo findOne(Long id){
		return phUserInfoRepository.findOne(id);
	}

	@Override
	public PhUserInfo findByUnionid(String unionid) {
		return phUserInfoRepository.findByUnionid(unionid);
	}
	
	@Override
	public Page<PhUserInfo> findByPage(final String nickname,final String unionid,final String phone,final String isAuth,Pageable page){
		return phUserInfoRepository.findAll(new Specification<PhUserInfo>() {
			
			@Override
			public Predicate toPredicate(Root<PhUserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> params = new ArrayList<Predicate>();
				
				if(StringUtils.isNotBlank(nickname)){
					params.add(criteriaBuilder.like(root.get("nickname"), "%"+nickname+"%"));
				}
				
				if(StringUtils.isNotBlank(phone)){
					params.add(criteriaBuilder.equal(root.get("phone"), phone));
				}
				
				if(StringUtils.isNotBlank(unionid)){
					params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
				}
				
				if(StringUtils.isNotBlank(isAuth)){
					params.add(criteriaBuilder.equal(root.get("isAuth"), isAuth));
				}
				
                Predicate[] predicates = new Predicate[params.size()];
                criteriaQuery.where(params.toArray(predicates));
				return null;
			}
		}, page);
	}
}
