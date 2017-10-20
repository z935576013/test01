package com.merak.lzpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping("/login")
	public String adminLogin() {
		return "demo/login";
	}

}