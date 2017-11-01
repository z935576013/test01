package com.merak.lzpt.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SessionUtil {

	static final long EXPIRATIONTIME = 3600 * 24 * 7;
	static final String SECRET = "MERAK"; // JWT密码
	static final String COOKIE_NAME = "JWT";// 存放Token的cookie Key

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
		try {
			String token = CookieUtils.getCookie(request, COOKIE_NAME);
			if (token != null) {
				// 解析 Token
				Claims claims = Jwts.parser()
						// 验签
						.setSigningKey(SECRET)
						// 去掉 Bearer
						.parseClaimsJws(token).getBody();

				String id = claims.getSubject();
				UserInfo userInfo = new UserInfo();
				userInfo.setId(Long.valueOf(id));
				userInfo.setMobile(claims.get("mobile", String.class));
				userInfo.setName(claims.get("name", String.class));
				return userInfo;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取session中的userInfo
	 */
	public static UserInfo getUserInfo() {
		return getUserInfo(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());

	}

	/**
	 * 将userInfo设置到JWT中
	 */
	public static void setUserInfo(HttpServletResponse response, UserInfo userInfo) {
		// 生成JWT
		String JWT = Jwts.builder().claim("mobile", userInfo.getMobile()).claim("name", userInfo.getName())
				// 用户名写入标题
				.setSubject(userInfo.getId().toString())
				// 有效期设置
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				// 签名设置
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		CookieUtils.setCookie(response, COOKIE_NAME, JWT, "/");
	}

}
