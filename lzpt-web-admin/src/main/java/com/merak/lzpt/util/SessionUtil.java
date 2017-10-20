package com.merak.lzpt.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.merak.lzpt.util.AdminInfo;

public class SessionUtil {

	public static final String SESSION_ADMIN = "sessionAdmin";

	/**
	 * 获取session中的adminId
	 */
	public static Long getAdminId() {
		AdminInfo adminInfo = getAdminInfo();
		if (adminInfo == null || adminInfo.getId() == null || adminInfo.getId().longValue() == 0) {
			return null;
		} else {
			return adminInfo.getId();
		}
	}

	/**
	 * 获取session中的adminId
	 * 
	 * @return
	 */
	public static AdminInfo getAdminInfo(HttpServletRequest request) {
		// Object admin = request.getSession(true).getAttribute(SESSION_ADMIN);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object admin = authentication.getPrincipal();
			if (admin == null) {
				return null;
			} else {
				return (AdminInfo) admin;
			}
		} else {
			return null;
		}
	}

	/**
	 * 获取session中的Admin
	 */
	public static AdminInfo getAdminInfo() {
		return getAdminInfo(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());

	}
}
