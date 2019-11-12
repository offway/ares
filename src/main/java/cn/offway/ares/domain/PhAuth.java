package cn.offway.ares.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 用户认证
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
@Entity
@Table(name = "ph_auth")
public class PhAuth implements Serializable {

    /** ID **/
    private Long id;

    /** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。 **/
    private String unionid;

    /** 用户昵称 **/
    private String nickname;

    /** 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 **/
    private String headimgurl;

    /** 状态[0-申请,1-通过,2-失败] **/
    private String status;

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

    /** 创建时间 **/
    private Date createTime;

    /** 审批人 **/
    private String approver;

    /** 审批时间 **/
    private Date approval;

    /** 失败原因 **/
    private String approvalContent;
    
    /** 表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id **/
    private String formId;

    /** 备注 **/
    private String remark;
    
    /** 邀请码ID**/
    private Long codeId;


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

    @Column(name = "nickname", length = 200)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "headimgurl", length = 500)
    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    @Column(name = "status", length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "approver", length = 50)
    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "approval")
    public Date getApproval() {
        return approval;
    }

    public void setApproval(Date approval) {
        this.approval = approval;
    }

    @Column(name = "approval_content", length = 200)
    public String getApprovalContent() {
        return approvalContent;
    }

    public void setApprovalContent(String approvalContent) {
        this.approvalContent = approvalContent;
    }
    
    @Column(name = "form_id", length = 200)
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

    @Column(name = "remark", length = 200)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "code_id", length = 11)
	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}
    

}
