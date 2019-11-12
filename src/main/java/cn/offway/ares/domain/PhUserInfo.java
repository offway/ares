package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 用户信息
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Entity
@Table(name = "ph_user_info")
public class PhUserInfo implements Serializable {

    /** ID **/
    private Long id;

    /** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。 **/
    private String unionid;

    /** 微信小程序用户ID **/
    private String miniopenid;

    /** 用户昵称 **/
    private String nickname;

    /** 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 **/
    private String sex;

    /** 用户个人资料填写的省份 **/
    private String province;

    /** 普通用户个人资料填写的城市 **/
    private String city;

    /** 国家，如中国为CN **/
    private String country;

    /** 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 **/
    private String headimgurl;

    /** 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom） **/
    private String privilege;

    /** 是否认证[0-否,1-是] **/
    private String isAuth;

    /** 手机 **/
    private String phone;

    /** 企业名称 **/
    private String companyName;

    /** 职位 **/
    private String position;

    /** 姓名 **/
    private String realName;

    /** 身份证正面 **/
    private String idcardPositive;

    /** 身份证反面 **/
    private String idcardObverse;

    /** 在职证明 **/
    private String inCert;

    /** 信用分 **/
    private Long creditScore;

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

    @Column(name = "unionid", length = 200)
    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Column(name = "miniopenid", length = 50)
    public String getMiniopenid() {
        return miniopenid;
    }

    public void setMiniopenid(String miniopenid) {
        this.miniopenid = miniopenid;
    }

    @Column(name = "nickname", length = 200)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "sex", length = 2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "province", length = 50)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "city", length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "country", length = 200)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "headimgurl", length = 500)
    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    @Column(name = "privilege", length = 200)
    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Column(name = "is_auth", length = 2)
    public String getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth;
    }

    @Column(name = "phone", length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "company_name", length = 50)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "position", length = 20)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "real_name", length = 50)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Column(name = "idcard_positive", length = 50)
    public String getIdcardPositive() {
        return idcardPositive;
    }

    public void setIdcardPositive(String idcardPositive) {
        this.idcardPositive = idcardPositive;
    }

    @Column(name = "idcard_obverse", length = 50)
    public String getIdcardObverse() {
        return idcardObverse;
    }

    public void setIdcardObverse(String idcardObverse) {
        this.idcardObverse = idcardObverse;
    }

    @Column(name = "in_cert", length = 50)
    public String getInCert() {
        return inCert;
    }

    public void setInCert(String inCert) {
        this.inCert = inCert;
    }

    @Column(name = "credit_score", length = 11)
    public Long getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Long creditScore) {
        this.creditScore = creditScore;
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
