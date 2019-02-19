package cn.offway.athena.controller;

import java.io.ByteArrayInputStream;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.offway.athena.domain.PhOrderExpressDetail;
import cn.offway.athena.domain.PhOrderExpressInfo;
import cn.offway.athena.service.PhOrderExpressDetailService;
import cn.offway.athena.service.PhOrderExpressInfoService;

@RestController
@RequestMapping("/notify/sf")
public class NotifyController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PhOrderExpressDetailService phOrderExpressDetailService;
	
	@Autowired
	private PhOrderExpressInfoService phOrderExpressInfoService;
	
	/**
	 * 快递路由推送
	 * @param xml
	 * @param orderid
	 * @param mailno
	 * @return
	 */
	@RequestMapping("/route")
	public String route(@RequestBody String xml){
		
		try {
			logger.info("快递路由推送xml:"+xml);
			//<?xml version='1.0' encoding='UTF-8'?><Request service="RoutePushService" lang="zh-CN"><Body><WaybillRoute id="865038" mailno="444010263979" orderid="QIAO-20180104004" acceptTime="2019-02-12 18:11:30" acceptAddress="深圳市" remark="备注" opCode="80"/></Body></Request>
			SAXReader reader = new SAXReader();
			Document document = reader.read(new ByteArrayInputStream(xml.getBytes()));
			
			Element response = document.getRootElement();
			Element WaybillRoute = response.element("Body").element("WaybillRoute");
			String id = WaybillRoute.elementText("id");
			String mailNo = WaybillRoute.elementText("mailno");
			String orderNo = WaybillRoute.elementText("orderid");
			String acceptTime = WaybillRoute.elementText("acceptTime");//路由节点产生的时间，格式：YYYY-MM-DD HH24:MM:SS，示例：2012-7-30 09:30:00
			String acceptAddress = WaybillRoute.elementText("acceptAddress");//路由节点发生的城市
			String remark = WaybillRoute.elementText("remark");//路由节点具体描述

			PhOrderExpressDetail phOrderExpressDetail = new PhOrderExpressDetail();
			phOrderExpressDetail.setContent(remark);
			phOrderExpressDetail.setCreateTime(new Date());
			phOrderExpressDetail.setAcceptTime(acceptTime);
			phOrderExpressDetail.setExpressOrderNo(orderNo);
			phOrderExpressDetail.setMailNo(mailNo);
			phOrderExpressDetailService.save(phOrderExpressDetail);

			

			return "<?xml version=\'1.0\' encoding=\'UTF-8\'?><Response service=\'RoutePushService\' lang=\'zh-CN\'><Head>OK</Head></Response>";
		} catch (DocumentException e) {
			e.printStackTrace();
			return "<?xml version=\'1.0\' encoding=\'UTF-8\'?><Response service=\'RoutePushService\' lang=\'zh-CN\'><Head>OK</Head></Response>";

		}
	}
	
	/**
	 * 快递订单状态推送
	 * @param orderid
	 * @param content
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping("/state")
	public String state(String content) throws DocumentException{
		logger.info("快递订单状态推送 content:"+content);
		//<Request service="PushOrderState" lang="zh-CN"><orderNo>QIAO-20180104004</orderNo><waybillNo>444010263979</waybillNo><orderStateCode>40001</orderStateCode><orderStateDesc>调度成功,收派员信息</orderStateDesc><empCode>000212</empCode><empPhone>13912345678</empPhone><netCode>755</netCode><lastTime>2019-02-12 18:14:17</lastTime><bookTime>2019-02-12 18:14:17</bookTime><carrierCode>SF</carrierCode></Request>
		SAXReader reader = new SAXReader();
		Document document = reader.read(new ByteArrayInputStream(content.getBytes()));
		
		Element response = document.getRootElement();
		String orderStateCode = response.elementText("orderStateCode");
		if("40001".equals(orderStateCode)){
			//调度成功
			String orderNo = response.elementText("orderNo");
			String waybillNo = response.elementText("waybillNo");
			String empPhone = response.elementText("empPhone");
			String lastTime = response.elementText("lastTime");//最晚上门时间
			String bookTime = response.elementText("bookTime");//客户预约时间
			
			PhOrderExpressInfo phOrderExpressInfo = phOrderExpressInfoService.findByExpressOrderNo(orderNo);
			phOrderExpressInfo.setExPhone(empPhone);
			phOrderExpressInfo.setLastTime(lastTime);
			phOrderExpressInfoService.save(phOrderExpressInfo);
		}
		
		
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Response><success>true</success></Response>";
	}
}
