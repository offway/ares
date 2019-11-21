package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 商品表
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Entity
@Table(name = "ph_goods")
public class PhGoods implements Serializable {

    /** ID **/
    private Long id;

    /** 名称 **/
    private String name;

    /** 封面图片 **/
    private String image;

    /** 品牌ID **/
    private Long brandId;

    /** 品牌名称 **/
    private String brandName;

    /** 品牌LOGO **/
    private String brandLogo;

    /** 是否自营[0-否,1-是] **/
    private String isOffway;

    /** 类别[男装,女装] **/
    private String type;

    /** 类目[未发售,长袖,外套,短裤,长裤等] **/
    private String category;

    /** 是否发售[0-否,1-是] **/
    private String isRelease;

    /** 状态[0-未上架,1-已上架] **/
    private String status;

    /** 创建时间 **/
    private Date createTime;

    /** 备注 **/
    private String remark;

    /** 类目二 **/
    private String kind;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Column(name = "type", length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "category", length = 20)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "is_release", length = 2)
    public String getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(String isRelease) {
        this.isRelease = isRelease;
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

    @Column(name = "remark", length = 200)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "category_details", length = 20)
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

}
