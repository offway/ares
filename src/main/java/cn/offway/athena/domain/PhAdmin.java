package cn.offway.athena.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-10-15 16:49:00 Exp $
 */
@Entity
@Table(name = "ph_admin")
public class PhAdmin implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4912345585788310081L;

	/**  **/
    private Long id;

    /**  **/
    private Date createdtime;

    /**  **/
    private String password;

    /**  **/
    private String username;
    
    /** 用户昵称 **/
    private String nickname;
    
    private Set<String> urls = new HashSet<>();
    
    private List<PhResource> resources = new ArrayList<>();
    
    private List<Long> brandIds = new ArrayList<>();



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

    @Column(name = "password", length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "username", length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name = "nickname", length = 255)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

    @Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

    @Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

    @Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

    @Transient
	@Override
	public boolean isEnabled() {
		return true;
	}

    @Transient
	public Set<String> getUrls() {
		return urls;
	}

	public void setUrls(Set<String> urls) {
		this.urls = urls;
	}

	@Transient
	public List<PhResource> getResources() {
		return resources;
	}

	public void setResources(List<PhResource> resources) {
		this.resources = resources;
	}

	@Transient
	public List<Long> getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(List<Long> brandIds) {
		this.brandIds = brandIds;
	}
	
	
	
	

}
