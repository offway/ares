package cn.offway.athena.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import cn.offway.athena.domain.PhAuth;

/**
 * 用户认证Service接口
 *
 * @author wn
 * @version $v: 1.0.0, $time:2018-02-12 11:26:00 Exp $
 */
public interface PhAuthService{

	PhAuth save(PhAuth phAuth);
	
	PhAuth findOne(Long id);

	Page<PhAuth> findByPage(String status, String nickName, String unionid, Pageable page);

	boolean authUpdate(Long id, String status, String approvalContent, Authentication authentication);
}
