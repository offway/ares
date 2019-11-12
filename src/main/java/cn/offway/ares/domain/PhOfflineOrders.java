package cn.offway.ares.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-09-27 13:41:55 Exp $
 */
@Entity
@Table(name = "ph_offline_orders")
public class PhOfflineOrders implements Serializable {

    /** Id **/
    private Long id;

    /** 订单id **/
    private String ordersNo;

    /** 开始时间 **/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 归还时间 **/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date returnTime;

    /** 姓名 **/
    private String realName;

    /** 职位 **/
    private String position;

    /** 使用者 **/
    private String users;

    /** 商品数量 **/
    private Long goodsCount;

    /** 创建时间 **/
    private Date createTime;

    /** 状态:[0-未寄出,1-已寄出,2-未收回,3-已部分收回,4-已收回] **/
    private String state;

    /** 备注 **/
    private String remark;

    /** 是否有留言:[0-无,1-有] **/
    private String message;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_time")
    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    @Column(name = "real_name", length = 50)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Column(name = "position", length = 100)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "users", length = 50)
    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    @Column(name = "goods_count", length = 11)
    public Long getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Long goodsCount) {
        this.goodsCount = goodsCount;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "state", length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "remark", length = 200)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "message", length = 2)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
