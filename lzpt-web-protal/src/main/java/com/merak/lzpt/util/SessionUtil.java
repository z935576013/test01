package com.merak.lzpt.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtil {

	public static final String SESSION_USERINFO = "sessionUser";

	/**
	 * 获取session中的userId
	 */
	public static Long getUserId() {
		UserInfo userInfo = getUserInfo();
		if (userInfo == null || userInfo.getId() == null || userInfo.getId().longValue() == 0) {
			return null;
		} else {
			return userInfo.getId();
		}
	}

	/**
	 * 获取session中的userInfo
	 * 
	 * @return
	 */
	public static UserInfo getUserInfo(HttpServletRequest request) {
		Object user = request.getSession(true).getAttribute(SESSION_USERINFO);
		if (user == null) {
			return null;
		} else {
			return (UserInfo) user;
		}
	}

	/**
	 * 获取session中的userInfo
	 */
	public static UserInfo getUserInfo() {
		return getUserInfo(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());

	}

	/**
	 * 将userInfo设置到session中
	 */
	public static void setUserInfo(HttpServletRequest request, UserInfo userInfo) {
		if (request.getSession() != null) {
			request.getSession().invalidate();
		}
		request.getSession(true).setAttribute(SESSION_USERINFO, userInfo);
	}

}
