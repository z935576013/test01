package com.merak.lzpt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;
import com.merak.lzpt.util.ErrorMessage;
import com.merak.lzpt.util.ResultBean;
import com.merak.lzpt.util.SessionUtil;

@WebFilter(filterName = "authFilter", urlPatterns = "/*")
public class AuthFilter extends OncePerRequestFilter {

	private static final String[] USER_AUTH_URL = { "/demo/**" };

	private AntPathMatcher matcher = new AntPathMatcher();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String url = getRequestPath(request);

		boolean userAuth = true;
		boolean match = false;

		for (String pattern : USER_AUTH_URL) {
			if (matcher.match(pattern, url)) {
				match = true;
				if (SessionUtil.getUserId() == null) {
					userAuth = false;
				}
				break;
			}
		}

		if (match) {
			if (!userAuth) {
				ResultBean result = new ResultBean(ErrorMessage.ERROR_0001);
				response.setHeader("Content-Type", "application/json;charset=UTF-8");
				response.getWriter().write(JSON.toJSONString(result));
				response.flushBuffer();
				return;
			} else {
				filterChain.doFilter(request, response);
				return;
			}
		} else {
			filterChain.doFilter(request, response);
			return;
		}
	}

	private String getRequestPath(HttpServletRequest request) {
		String url = request.getServletPath();

		if (request.getPathInfo() != null) {
			url += request.getPathInfo();
		}

		url = url.toLowerCase();

		return url;
	}

}
