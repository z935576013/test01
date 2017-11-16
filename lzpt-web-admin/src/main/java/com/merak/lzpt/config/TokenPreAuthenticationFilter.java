package com.merak.lzpt.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.merak.lzpt.util.SessionUtil;

public class TokenPreAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
	
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		String token = SessionUtil.getTokenFromRequest(request);
		return token;
	}

	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return "N/A";
	}

}
