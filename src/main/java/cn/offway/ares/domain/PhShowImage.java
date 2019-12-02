package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 返图
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Entity
@Table(name = "ph_show_image")
public class PhShowImage implements Serializable {

	/** ID **/
	private Long id;

	/** 订单号 **/
	private String orderNo;

	/** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。 **/
	private String unionid;
	
	/** 职位 **/
    private String position;

    /** 姓名 **/
    private String realName;

	/** 品牌ID **/
	private Long brandId;

	/** 品牌名称 **/
	private String brandName;

	/** 品牌LOGO **/
	private String brandLogo;

	/** 是否自营[0-否,1-是] **/
	private String isOffway;

	/** 图片 **/
	private String showImage;

	/** 内容 **/
	private String content;

	/** 链接 **/
	private String url;

	/** 状态[0-提交,1-通过,2-拒绝] **/
	private String status;

	/** 审核人 **/
	private String checkName;

	/** 审核时间 **/
	private Date checkTime;

	/** 拒绝原因 **/
	private String checkContent;
	
	/** 使用明星  **/
	private String starName;
	
	/** 创建时间 **/
	private Date createTime;

	/** 备注 **/
	private String remark;

	/** 批次 **/
	private String batch;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "order_no", length = 50)
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	@Column(name = "brand_logo", length = 50)
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

	@Column(name = "show_image", length = 100)
	public String getShowImage() {
		return showImage;
	}

	public void setShowImage(String showImage) {
		this.showImage = showImage;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "status", length = 2)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "check_name", length = 50)
	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "check_time")
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "check_content", length = 200)
	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	@Column(name = "content", length = 500)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "url", length = 500)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "unionid", length = 200)
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	@Column(name = "star_name", length = 200)
	public String getStarName() {
		return starName;
	}

	public void setStarName(String starName) {
		this.starName = starName;
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

	@Column(name = "batch", length = 5)
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}
}
