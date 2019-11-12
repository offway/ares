package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * VIEW
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Entity
@Table(name = "v_order")
public class VOrder implements Serializable {

    /** ID **/
    private Long id;

    /** 订单号 **/
    private String orderNo;

    /** 使用日期 **/
    private Date useDate;

    /** 使用者 **/
    private String users;

    /** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。 **/
    private String unionid;

    /** 品牌ID **/
    private Long brandId;

    /** 品牌名称 **/
    private String brandName;

    /** 品牌LOGO **/
    private String brandLogo;

    /** 是否自营[0-否,1-是] **/
    private String isOffway;

    /** 状态[0-已下单,1-已发货,2-已寄回,3-已收货] **/
    private String status;
    
    /** 是否返图[0-否,1-是]**/
    private String isUpload;

    /** 创建时间 **/
    private Date createTime;

    /** 寄回时间 **/
    private Date returnTime;
    
    /** 返图时间 **/
    private Date showTime;
    
    /** 收货时间 **/
    private Date receiptTime;

    /** 备注 **/
    private String remark;
    
    /** 物流信息ID **/
    private Long eId;

    /** 物流订单号 **/
    private String expressOrderNo;

    /** 寄件方城市 **/
    private String fromCity;

    /** 寄件方详细地址 **/
    private String fromContent;

    /** 寄件方区/县 **/
    private String fromCounty;

    /** 寄件方手机 **/
    private String fromPhone;

    /** 寄件方省份 **/
    private String fromProvince;

    /** 寄件方姓名 **/
    private String fromRealName;

    /** 快递运单号 **/
    private String mailNo;

    /** 状态[0-新建,1-已下单,2-已接单,3-运送中,4-已签收] **/
    private String eStatus;

    /** 收件方城市 **/
    private String toCity;

    /** 收件方详细地址 **/
    private String toContent;

    /** 收件方区/县 **/
    private String toCounty;

    /** 收件方手机 **/
    private String toPhone;

    /** 收件方省份 **/
    private String toProvince;

    /** 收件方姓名 **/
    private String toRealName;
    
    /** 职位 **/
    private String position;

    /** 姓名 **/
    private String realName;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "e_id", length = 11)
    public Long geteId() {
		return eId;
	}

	public void seteId(Long eId) {
		this.eId = eId;
	}

	@Column(name = "e_status", length = 2)
	public String geteStatus() {
		return eStatus;
	}

	public void seteStatus(String eStatus) {
		this.eStatus = eStatus;
	}

	@Column(name = "order_no", length = 50)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "use_date")
    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    @Column(name = "users", length = 50)
    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    @Column(name = "unionid", length = 200)
    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Column(name = "brand_id", length = 11)
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    @Column(name = "brand_name", length = 50)
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Column(name = "brand_logo", length = 200)
    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    @Column(name = "is_offway", length = 2)
    public String getIsOffway() {
        return isOffway;
    }

    public void setIsOffway(String isOffway) {
        this.isOffway = isOffway;
    }

    @Column(name = "status", length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_time")
    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "show_time")
    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    @Column(name = "remark", length = 200)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "express_order_no", length = 50)
    public String getExpressOrderNo() {
        return expressOrderNo;
    }

    public void setExpressOrderNo(String expressOrderNo) {
        this.expressOrderNo = expressOrderNo;
    }

    @Column(name = "from_city", length = 20)
    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    @Column(name = "from_content", length = 200)
    public String getFromContent() {
        return fromContent;
    }

    public void setFromContent(String fromContent) {
        this.fromContent = fromContent;
    }

    @Column(name = "from_county", length = 20)
    public String getFromCounty() {
        return fromCounty;
    }

    public void setFromCounty(String fromCounty) {
        this.fromCounty = fromCounty;
    }

    @Column(name = "from_phone", length = 50)
    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    @Column(name = "from_province", length = 20)
    public String getFromProvince() {
        return fromProvince;
    }

    public void setFromProvince(String fromProvince) {
        this.fromProvince = fromProvince;
    }

    @Column(name = "from_real_name", length = 50)
    public String getFromRealName() {
        return fromRealName;
    }

    public void setFromRealName(String fromRealName) {
        this.fromRealName = fromRealName;
    }

    @Column(name = "mail_no", length = 50)
    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    @Column(name = "to_city", length = 20)
    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    @Column(name = "to_content", length = 200)
    public String getToContent() {
        return toContent;
    }

    public void setToContent(String toContent) {
        this.toContent = toContent;
    }

    @Column(name = "to_county", length = 20)
    public String getToCounty() {
        return toCounty;
    }

    public void setToCounty(String toCounty) {
        this.toCounty = toCounty;
    }

    @Column(name = "to_phone", length = 50)
    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    @Column(name = "to_province", length = 20)
    public String getToProvince() {
        return toProvince;
    }

    public void setToProvince(String toProvince) {
        this.toProvince = toProvince;
    }

    @Column(name = "to_real_name", length = 50)
    public String getToRealName() {
        return toRealName;
    }

    public void setToRealName(String toRealName) {
        this.toRealName = toRealName;
    }

    @Column(name = "is_upload", length = 2)
    public String getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "receipt_time")
	public Date getReceiptTime() {
		return receiptTime;
	}

	public void setReceiptTime(Date receiptTime) {
		this.receiptTime = receiptTime;
	}
	
	@Column(name = "position", length = 20)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "real_name", length = 50)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    

}
