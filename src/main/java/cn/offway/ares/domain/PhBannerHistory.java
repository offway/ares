package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * Banner 历史
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-10-10 14:00:40 Exp $
 */
@Entity
@Table(name = "ph_banner_history")
public class PhBannerHistory implements Serializable {

    /** ID **/
    private Long id;

    /** banner **/
    private String banner;

    /** banner id **/
    private String bannerId;

    /** banner img **/
    private String bannerImg;

    /** 创建时间 **/
    private Date createTime;

    /** 开始时间 **/
    private Date beginTime;

    /** 截止时间 **/
    private Date endTime;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "banner", length = 100)
    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @Column(name = "banner_id", length = 5)
    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    @Column(name = "banner_img", length = 200)
    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "begin_time")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
