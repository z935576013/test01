package com.merak.lzpt.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
public class CookieUtils {

	public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, "/", null);
	}

	public static void setCookie(HttpServletResponse response, String name, String value, String path) {
		setCookie(response, name, value, path, null);
	}

	public static void setCookie(HttpServletResponse response, String name, String value, Integer maxAge) {
		setCookie(response, name, value, "/", maxAge);
	}

	public static void setCookie(HttpServletResponse response, String name, String value, String path, Integer maxAge) {
		Cookie cookie = new Cookie(name, null);
		cookie.setHttpOnly(true);
		cookie.setPath(path);
		if (maxAge != null) {
			cookie.setMaxAge(maxAge);
		}
		cookie.setValue(value);
		response.addCookie(cookie);
	}

	public static String getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
