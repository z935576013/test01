package com.merak.lzpt.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.merak.lzpt.service.dto.Demo;
import com.merak.lzpt.service.inft.DemoService;
import com.merak.lzpt.util.ResultBean;

@RestController
@RequestMapping(value = "/demo", produces = "application/json; charset=UTF-8")
public class DemoController {

	@Autowired
	DemoService demoService;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@RequestMapping("/demo1")
	public ResultBean demo1(Long id) {
		ResultBean result = new ResultBean();
		Demo demo = demoService.getDemo(id);
		result.addAttribute("demo", demo);
		return result;
	}

	@RequestMapping("/demo2")
	public ResultBean demo2(Long id) {
		ResultBean result = new ResultBean();
		Demo demo = demoService.getDemo2(id);
		result.addAttribute("demo", demo);
		return result;
	}

	@RequestMapping("/deleteDemo")
	public ResultBean deleteDemo(Long id) {
		ResultBean result = new ResultBean();
		demoService.deleteDemo(id);
		return result;
	}

	@RequestMapping("/demo3")
	public ResultBean demo3(MultipartFile file) throws IOException {
		ResultBean result = new ResultBean();
		File tempFile = File.createTempFile("merak", ".temp");
		file.transferTo(tempFile);
		String path = demoService.saveFile(tempFile.getAbsolutePath());
		result.addAttribute("path", path);
		return result;
	}

}