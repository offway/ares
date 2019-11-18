package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 商品种类
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-11-18 14:49:58 Exp $
 */
@Entity
@Table(name = "ph_goods_kind")
public class PhGoodsKind implements Serializable {

    /** ID **/
    private Long id;

    /** 商品类别ID **/
    private Long goodsType;

    /** 商品类别名称 **/
    private String goodsTypeName;

    /** 商品类目ID **/
    private Long goodsCategory;

    /** 商品类目名称 **/
    private String goodsCategoryName;

    /** 名称 **/
    private String name;

    /** 图片 **/
    private String image;

    /** 排序 **/
    private Long sort;

    /** 创建时间 **/
    private Date createTime;

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

    @Column(name = "goods_type", length = 11)
    public Long getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Long goodsType) {
        this.goodsType = goodsType;
    }

    @Column(name = "goods_type_name", length = 100)
    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    @Column(name = "goods_category", length = 11)
    public Long getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(Long goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    @Column(name = "goods_category_name", length = 100)
    public String getGoodsCategoryName() {
        return goodsCategoryName;
    }

    public void setGoodsCategoryName(String goodsCategoryName) {
        this.goodsCategoryName = goodsCategoryName;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "image", length = 100)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(name = "sort", length = 11)
    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
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

}
