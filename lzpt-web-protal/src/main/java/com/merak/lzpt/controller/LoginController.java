package com.merak.lzpt.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merak.lzpt.util.ResultBean;
import com.merak.lzpt.util.SessionUtil;
import com.merak.lzpt.util.UserInfo;

@RestController
@RequestMapping(value = "/login", produces = "application/json; charset=UTF-8")
public class LoginController {

	@RequestMapping("/userLogin")
	public ResultBean userLogin(HttpServletResponse response) {
		ResultBean result = new ResultBean();
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1L);
		userInfo.setMobile("mo");
		userInfo.setName("na");
		SessionUtil.setUserInfo(response, userInfo);
		return result;
	}

}