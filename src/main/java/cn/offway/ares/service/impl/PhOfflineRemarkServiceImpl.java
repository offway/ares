package cn.offway.ares.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import cn.offway.ares.service.PhOfflineRemarkService;

import cn.offway.ares.domain.PhOfflineRemark;
import cn.offway.ares.repository.PhOfflineRemarkRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


/**
 * 线下订单留言Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-10-09 13:33:39 Exp $
 */
@Service
public class PhOfflineRemarkServiceImpl implements PhOfflineRemarkService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhOfflineRemarkRepository phOfflineRemarkRepository;
	
	@Override
	public PhOfflineRemark save(PhOfflineRemark phOfflineRemark){
		return phOfflineRemarkRepository.save(phOfflineRemark);
	}
	
	@Override
	public PhOfflineRemark findOne(Long id){
		return phOfflineRemarkRepository.findOne(id);
	}

	@Override
	public void delete(Long id){
		phOfflineRemarkRepository.delete(id);
	}

	@Override
	public List<PhOfflineRemark> save(List<PhOfflineRemark> entities){
		return phOfflineRemarkRepository.save(entities);
	}

	@Override
	public Page<PhOfflineRemark> findAllByPage(String id, Pageable page){
		return phOfflineRemarkRepository.findAll(new Specification<PhOfflineRemark>() {
			@Override
			public Predicate toPredicate(Root<PhOfflineRemark> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> params = new ArrayList<Predicate>();
				if (StringUtils.isNotBlank(id)){
					params.add(criteriaBuilder.equal(root.get("ordersId"),id));
				}
				Predicate[] predicates = new Predicate[params.size()];
				criteriaQuery.where(params.toArray(predicates));
				return null;
			}
		},page);
	}
}
