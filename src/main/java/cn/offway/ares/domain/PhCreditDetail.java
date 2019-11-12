package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 信用明细
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Entity
@Table(name = "ph_credit_detail")
public class PhCreditDetail implements Serializable {

    /** ID **/
    private Long id;

    /** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。 **/
    private String unionid;

    /** 渠道 **/
    private String channel;

    /** 类型[0-加,1-减] **/
    private String type;

    /** 信用分 **/
    private Long score;

    /** 创建时间 **/
    private Date createTime;

    /** 备注 **/
    private String remark;
    
    /** 订单号 **/
    private String orderNo;
    
    /** 创建者 **/
    private String createName;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "unionid", length = 200)
    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Column(name = "channel", length = 2)
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Column(name = "type", length = 2)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "score", length = 11)
    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
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
    
    @Column(name = "order_no", length = 50)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "create_name", length = 50)
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
    
    

}
