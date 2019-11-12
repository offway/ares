package cn.offway.ares.domain;

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
@Table(name = "ph_offline_orders_goods")
public class PhOfflineOrdersGoods implements Serializable {

    /** id **/
    private Long id;

    /** 订单id **/
    private String ordersNo;

    /** 商品ID **/
    private Long goodsId;

    /** 商品名称 **/
    private String goodsName;

    /** 封面图片 **/
    private String goodsImage;

    /** 品牌ID **/
    private Long brandId;

    /** 品牌名称 **/
    private String brandName;

    /** 品牌LOGO **/
    private String brandLogo;

    /** SKU **/
    private String sku;

    /** 尺码 **/
    private String size;

    /** 颜色 **/
    private String color;

    /** 创建时间 **/
    private Date createTime;

    /** 物流单号 **/
    private String expressOrderNo;

    /** 寄出方式:[0-平台,1-商家] **/
    private String way;

    /** 备注 **/
    private String remark;

    /** 状态:[0-未收回,1-已收回] **/
    private String state;

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

    @Column(name = "goods_id", length = 11)
    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    @Column(name = "goods_name", length = 100)
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Column(name = "goods_image", length = 100)
    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
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

    @Column(name = "sku", length = 100)
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Column(name = "size", length = 10)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(name = "color", length = 10)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "express_order_no", length = 50)
    public String getExpressOrderNo() {
        return expressOrderNo;
    }

    public void setExpressOrderNo(String expressOrderNo) {
        this.expressOrderNo = expressOrderNo;
    }

    @Column(name = "way", length = 2)
    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    @Column(name = "remark", length = 200)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "state", length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
