package com.merak.lzpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.merak.lzpt.service.dto.Demo;
import com.merak.lzpt.service.inft.DemoService;
import com.merak.lzpt.util.AdminInfo;
import com.merak.lzpt.util.SessionUtil;

@Controller
@RequestMapping(value = "/", produces = "application/json; charset=UTF-8")
public class DemoController2 {

	@Autowired
	DemoService demoService;

	@RequestMapping("/")
	public String demo1(Long id, Model model) {
		if (id == null) {
			id = 1L;
		}
		AdminInfo admin = SessionUtil.getAdminInfo();
		Demo demo = demoService.getDemo(id);
		model.addAttribute("demo", demo);
		model.addAttribute("admin", admin);
		return "demo/index";
	}

}