package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 返图筛选
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-04-04 15:18:00 Exp $
 */
@Entity
@Table(name = "ph_show_image_filter")
public class PhShowImageFilter implements Serializable {

    /** ID **/
    private Long id;

    /** 品牌ID **/
    private Long brandId;

    /** 品牌名称 **/
    private String brandName;

    /** 品牌LOGO **/
    private String brandLogo;

    /** 使用日期 **/
    private Date useDate;

    /** 图片 **/
    private String showImage;

    /** 明星姓名 **/
    private String starName;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "use_date")
    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    @Column(name = "show_image")
    public String getShowImage() {
        return showImage;
    }

    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }

    @Column(name = "star_name", length = 200)
    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
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
