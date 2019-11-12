package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 订单物流详情
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Entity
@Table(name = "ph_order_express_detail")
public class PhOrderExpressDetail implements Serializable {

    /** ID **/
    private Long id;

    /** 路由节点产生的时间，格式：YYYY-MM-DD HH24:MM:SS，示例：2012-7-30 09:30:00 **/
    private String acceptTime;

    /** 物流订单号 **/
    private String expressOrderNo;

    /** 快递运单号 **/
    private String mailNo;

    /** 内容 **/
    private String content;

    /** 创建时间 **/
    private Date createTime;
    
    /** 路由节点发生的城市 **/
    private String acceptAddress;
    
    /** 路由信息操作码 **/
    private String opCode;

    /** 备注 **/
    private String remark;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "accept_time", length = 50)
	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	@Column(name = "express_order_no", length = 50)
    public String getExpressOrderNo() {
        return expressOrderNo;
    }

    public void setExpressOrderNo(String expressOrderNo) {
        this.expressOrderNo = expressOrderNo;
    }

    @Column(name = "mail_no", length = 50)
    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    @Column(name = "content", length = 200)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Column(name = "accept_address", length = 100)
	public String getAcceptAddress() {
		return acceptAddress;
	}

	public void setAcceptAddress(String acceptAddress) {
		this.acceptAddress = acceptAddress;
	}

    @Column(name = "op_code", length = 20)
	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
    
    

}
