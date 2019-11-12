package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 线下订单留言
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-10-09 13:33:39 Exp $
 */
@Entity
@Table(name = "ph_offline_remark")
public class PhOfflineRemark implements Serializable {

    /** id **/
    private Long id;

    /** 订单编号 **/
    private String ordersNo;

    /** 订单id **/
    private String ordersId;

    /** 内容 **/
    private String content;

    /** 备注 **/
    private String remark;

    /** 创建时间 **/
    private Date createTime;

    /** 昵称 **/
    private String nickname;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "orders_no", length = 50)
    public String getOrdersNo() {
        return ordersNo;
    }

    public void setOrdersNo(String ordersNo) {
        this.ordersNo = ordersNo;
    }

    @Column(name = "orders_id", length = 50)
    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    @Column(name = "content", length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "remark", length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "nickname", length = 255)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
