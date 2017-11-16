package com.merak.lzpt.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SessionUtil {

	static final long EXPIRATIONTIME = 3600 * 24 * 7;
	static final String SECRET = "MERAK"; // JWT密码
	static final String TOKEN_PREFIX = "Bearer"; // Token前缀
	static final String COOKIE_NAME = "USER_TOKEN";// 存放Token的cookie Key

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
	 * 获取session中的AdminInfo
	 * 
	 * @return
	 */
	public static AdminInfo getAdminInfo(HttpServletRequest request) {
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

	public static String getTokenFromRequest(HttpServletRequest request) {
		return CookieUtils.getCookie(request, COOKIE_NAME);
	}

	public static AdminInfo getAdminInfoFromToken(String token) {
		if (token != null) {
			// 解析 Token
			try {
				Claims claims = Jwts.parser()
						// 验签
						.setSigningKey(SECRET)
						// 去掉 Bearer
						.parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();

				String id = claims.getSubject();
				AdminInfo adminInfo = new AdminInfo();
				adminInfo.setId(Long.valueOf(id));
				adminInfo.setMobile(claims.get("mobile", String.class));
				adminInfo.setName(claims.get("name", String.class));
				adminInfo.setAuthoritys(claims.get("authoritys", String.class));
				return adminInfo;
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 将adminInfo设置到JWT中
	 */
	public static void setAdminInfo(HttpServletResponse response, AdminInfo adminInfo) {
		// 生成JWT
		String JWT = Jwts.builder().claim("mobile", adminInfo.getMobile()).claim("name", adminInfo.getName())
				.claim("authoritys", adminInfo.getAuthoritys())
				// 用户名写入标题
				.setSubject(adminInfo.getId().toString())
				// 有效期设置
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				// 签名设置
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		CookieUtils.setCookie(response, COOKIE_NAME, JWT, "/");
	}

	// /**
	// * 获取session中的adminId
	// *
	// * @return
	// */
	// public static AdminInfo getAdminInfo(HttpServletRequest request) {
	// // Object admin = request.getSession(true).getAttribute(SESSION_ADMIN);
	// Authentication authentication =
	// SecurityContextHolder.getContext().getAuthentication();
	// if (authentication != null) {
	// Object admin = authentication.getPrincipal();
	// if (admin == null) {
	// return null;
	// } else {
	// return (AdminInfo) admin;
	// }
	// } else {
	// return null;
	// }
	// }

	/**
	 * 获取session中的Admin
	 */
	public static AdminInfo getAdminInfo() {
		return getAdminInfo(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());

	}
}
