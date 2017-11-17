package com.merak.lzpt.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.merak.lzpt.util.AdminInfo;
import com.merak.lzpt.util.SessionUtil;

public class TokenAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		Object obj = authentication.getPrincipal();
		AdminInfo adminInfo = (AdminInfo) obj;
		SessionUtil.setAdminInfo(response, adminInfo);
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
