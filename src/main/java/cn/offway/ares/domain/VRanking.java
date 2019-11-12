package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * VIEW
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-09-05 12:57:03 Exp $
 */
@Entity
@Table(name = "v_ranking")
public class VRanking implements Serializable {

    /** 品牌ID **/
    private Long brandId;

    /** 品牌LOGO **/
    private String brandLogo;

    /** 品牌名称 **/
    private String brandName;

    /** 总结借出数 **/
    private Long sumgoods;

    /** 总返图 **/
    private Long upload;

    @Id
    @Column(name = "brand_id", length = 11)
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    @Column(name = "brand_logo", length = 200)
    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    @Column(name = "brand_name", length = 50)
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Column(name = "sumgoods", length = 21)
    public Long getSumgoods() {
        return sumgoods;
    }

    public void setSumgoods(Long sumgoods) {
        this.sumgoods = sumgoods;
    }

    @Column(name = "upload", length = 21)
    public Long getUpload() {
        return upload;
    }

    public void setUpload(Long upload) {
        this.upload = upload;
    }

}
