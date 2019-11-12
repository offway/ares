package cn.offway.ares.service;

import java.io.ByteArrayInputStream;

import cn.offway.ares.dto.sf.ReqAddOrder;
import cn.offway.ares.utils.JsonResult;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sf.csim.express.service.CallExpressServiceTools;


/**
 * 顺丰服务
 * @author wn
 *
 */
@Service
public class SfExpressService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${sf.url}")
	private String url;
	
	@Value("${sf.client-code}")
	private String clientCode;
	
	@Value("${sf.checkword}")
	private String checkword;
	
	private static final String REQ_ADD_ORDER = "<Request service = \"OrderService\" lang = \"zh-CN\" > \n<Head>CLIENTCODE</Head>\n<Body>\n  <Order \n\torderid=\"ORDERID\" \n\tis_gen_bill_no=\"1\" \n\tj_contact=\"J_CONTACT\" \n\tj_tel=\"J_TEL\" \n\tj_mobile=\"J_TEL\" \n\tj_province=\"J_PROVINCE\" \n\tj_city=\"J_CITY\"\n\tj_county=\"J_COUNTY\"\n\tj_address=\"J_ADDRESS\" \n\td_contact=\"D_CONTACT\"\n\td_tel=\"D_TEL\" \n\td_mobile=\"D_TEL\" \n\td_province=\"D_PROVINCE\" \n\td_city=\"D_CITY\" \n\td_county=\"D_COUNTY\" \n\td_address=\"D_ADDRESS\" \n\texpress_type=\"1\" \n\tpay_method=\"PAY_METHOD\" \n\tparcel_quantity=\"1\" \n\tis_docall=\"1\" \n\tsendstarttime=\"SENDSTARTTIME\" \n\torder_source=\"ORDER_SOURCE\" \n\tremark=\"REMARK\">\n  <Cargo name='服装' /> \n</Order>\n</Body> \n</Request>";
	
	/**
	 * 在线下单
	 * @return
	 */
	@SuppressWarnings("static-access")
	public JsonResult addOrder(ReqAddOrder addOrder){
		
		try {
			String req = REQ_ADD_ORDER;
			req = req.replaceAll("CLIENTCODE", clientCode);
			req = req.replaceAll("ORDERID", addOrder.getOrder_id());
			req = req.replaceAll("J_CONTACT", addOrder.getJ_contact());
			req = req.replaceAll("J_TEL", addOrder.getJ_tel());
			req = req.replaceAll("J_PROVINCE", addOrder.getJ_province());
			req = req.replaceAll("J_CITY", addOrder.getJ_city());
			req = req.replaceAll("J_COUNTY", addOrder.getJ_county());
			req = req.replaceAll("J_ADDRESS", addOrder.getJ_address());
			req = req.replaceAll("D_CONTACT", addOrder.getD_contact());
			req = req.replaceAll("D_TEL", addOrder.getD_tel());
			req = req.replaceAll("D_PROVINCE", null==addOrder.getD_province()?"":addOrder.getD_province());
			req = req.replaceAll("D_CITY", null==addOrder.getD_city()?"":addOrder.getD_city());
			req = req.replaceAll("D_COUNTY", null==addOrder.getD_county()?"":addOrder.getD_county());
			req = req.replaceAll("D_ADDRESS", addOrder.getD_address());
			req = req.replaceAll("PAY_METHOD", addOrder.getPay_method());//付款方式：1:寄方付2:收方付3:第三方付
			req = req.replaceAll("SENDSTARTTIME", addOrder.getSendstarttime());
			req = req.replaceAll("ORDER_SOURCE", addOrder.getOrder_source());
			req = req.replaceAll("REMARK", addOrder.getRemark());
			
			logger.info("请求报文："+req);
			CallExpressServiceTools client=CallExpressServiceTools.getInstance();     
			String respXml= client.callSfExpressServiceByCSIM(url, req, clientCode, checkword);
			logger.info("响应报文："+respXml);
			SAXReader reader = new SAXReader();
			Document document = reader.read(new ByteArrayInputStream(respXml.getBytes()));
			
			Element response = document.getRootElement();
			String head = response.elementText("Head");
			if("OK".equals(head)){
				Element orderResponse = response.element("Body").element("OrderResponse");
				String mailno = orderResponse.attributeValue("mailno");
				return new JsonResult("200", null, mailno);
			}else{
				Element error =  response.element("ERROR");
				String code = error.attributeValue("code");
				String text = error.getText();
				return new JsonResult(code,text, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult("500", "系统错误", null);
		}
	}
	
}
