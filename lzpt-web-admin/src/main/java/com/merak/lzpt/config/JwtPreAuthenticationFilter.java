package com.merak.lzpt.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.merak.lzpt.util.SessionUtil;

public class JwtPreAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

	static final String COOKIE_NAME = "USER_TOKEN";// 存放Token的cookie Key

	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		String token = SessionUtil.getTokenFromRequest(request);
		return token;
	}

	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return "N/A";
	}

}
