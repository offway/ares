/**
 * 
 */
package cn.offway.ares.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

/**
 * @author wn
 *
 */
public interface RbacService {
	
	boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
