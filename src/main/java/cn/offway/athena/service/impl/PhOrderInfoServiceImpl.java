package cn.offway.athena.service.impl;

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

import cn.offway.athena.domain.PhOrderExpressInfo;
import cn.offway.athena.domain.PhOrderInfo;
import cn.offway.athena.domain.VOrder;
import cn.offway.athena.dto.sf.ReqAddOrder;
import cn.offway.athena.repository.PhOrderInfoRepository;
import cn.offway.athena.service.PhOrderExpressInfoService;
import cn.offway.athena.service.PhOrderInfoService;
import cn.offway.athena.service.SfExpressService;
import cn.offway.athena.utils.JsonResult;


/**
 * 订单Service接口实现
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Service
public class PhOrderInfoServiceImpl implements PhOrderInfoService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhOrderInfoRepository phOrderInfoRepository;
	
	@Autowired
	private SfExpressService sfExpressService;
	
	@Autowired
	private PhOrderExpressInfoService phOrderExpressInfoService;
	
	@Override
	public PhOrderInfo save(PhOrderInfo phOrderInfo){
		return phOrderInfoRepository.save(phOrderInfo);
	}
	
	@Override
	public PhOrderInfo findOne(Long id){
		return phOrderInfoRepository.findOne(id);
	}
	
	@Override
	public String generateOrderNo(String prefix){
		int count = phOrderInfoRepository.hasOrder(prefix);
		if(count == 0){
			phOrderInfoRepository.insert(prefix);
		}
		return phOrderInfoRepository.generateOrderNo(prefix);
	}
	
	@Override
	public PhOrderInfo findByOrderNo(String orderNo){
		return phOrderInfoRepository.findByOrderNo(orderNo);
	}
	
	@Override
	public Page<PhOrderInfo> findByPage(final String orderNo,final String unionid,Pageable page){
		return phOrderInfoRepository.findAll(new Specification<PhOrderInfo>() {
			
			@Override
			public Predicate toPredicate(Root<PhOrderInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> params = new ArrayList<Predicate>();
				
				if(StringUtils.isNotBlank(orderNo)){
					params.add(criteriaBuilder.equal(root.get("orderNo"), orderNo));
				}
				
				if(StringUtils.isNotBlank(unionid)){
					params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
				}
				
				
                Predicate[] predicates = new Predicate[params.size()];
                criteriaQuery.where(params.toArray(predicates));
				return null;
			}
		}, page);
	}
	
	
	@Override
	public JsonResult saveOrder(String orderNo){
		/**
		 * 1.修改订单状态 
		 * 2.快递预约上门
		 */
		
		PhOrderInfo phOrderInfo = findByOrderNo(orderNo);
		if("1".equals(phOrderInfo.getStatus())){
			return new JsonResult("500", "订单已发货！", null);
		}
		
		phOrderInfo.setStatus("1");
		
		
		PhOrderExpressInfo phOrderExpressInfo = phOrderExpressInfoService.findByOrderNoAndType(orderNo, "0");
		phOrderExpressInfo.setExpressOrderNo(generateOrderNo("SF"));
		
		ReqAddOrder addOrder = new ReqAddOrder();
		addOrder.setD_address(phOrderExpressInfo.getToContent());
		addOrder.setD_contact(phOrderExpressInfo.getToRealName());
		addOrder.setD_tel(phOrderExpressInfo.getToPhone());
		addOrder.setJ_province(phOrderExpressInfo.getFromProvince());
		addOrder.setJ_city(phOrderExpressInfo.getFromCity());
		addOrder.setJ_county(phOrderExpressInfo.getFromCounty());
		addOrder.setJ_address(phOrderExpressInfo.getFromContent());
		addOrder.setJ_contact(phOrderExpressInfo.getFromRealName());
		addOrder.setJ_tel(phOrderExpressInfo.getFromPhone());
		addOrder.setOrder_source("OFFWAY");
		addOrder.setOrder_id(phOrderExpressInfo.getExpressOrderNo());
		addOrder.setPay_method("2");//付款方式：1:寄方付2:收方付3:第三方付
		addOrder.setRemark("");
		addOrder.setSendstarttime("");
		JsonResult result = sfExpressService.addOrder(addOrder);
		if("200".equals(result.getCode())){
			String mailNo = String.valueOf(result.getData());
			phOrderExpressInfo.setMailNo(mailNo);
			phOrderExpressInfoService.save(phOrderExpressInfo);
			save(phOrderInfo);
		}
		return result;
		
	}
	
	
}
