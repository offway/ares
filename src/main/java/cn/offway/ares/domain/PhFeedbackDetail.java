package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 反馈表明细
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-09-25 15:10:52 Exp $
 */
@Entity
@Table(name = "ph_feedback_detail")
public class PhFeedbackDetail implements Serializable {

    /**  **/
    private Long id;

    /**  **/
    private Long pid;

    /**  **/
    private Long brandId;

    /**  **/
    private String brandName;

    /**  **/
    private String brandLogo;

    /**  **/
    private String starName;

    /**  **/
    private Long imgNum;

    /**  **/
    private String weibo;

    /**  **/
    private String remark;

    /**  **/
    private Date backTime;

    /**  **/
    private String imgUrl;

    /**  **/
    private String goodsId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "pid", length = 11)
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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

    @Column(name = "star_name", length = 50)
    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    @Column(name = "img_num", length = 11)
    public Long getImgNum() {
        return imgNum;
    }

    public void setImgNum(Long imgNum) {
        this.imgNum = imgNum;
    }

    @Column(name = "weibo", length = 200)
    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "back_time")
    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    @Column(name = "img_url", length = 1000)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Column(name = "goods_id", length = 1000)
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Column(name = "remark", length = 200)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
