package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 
 *
 * @author wn
 * @version $v: 1.0.0, $time:2019-04-01 11:26:00 Exp $
 */
@Entity
@Table(name = "ph_brandadmin")
public class PhBrandadmin implements Serializable {

    /**  **/
    private Long id;

    /**  **/
    private Date createdtime;

    /**  **/
    private Long adminId;

    /**  **/
    private Long brandId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdtime")
    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    @Column(name = "admin_id", length = 20)
    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    @Column(name = "brand_id", length = 11)
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

}
