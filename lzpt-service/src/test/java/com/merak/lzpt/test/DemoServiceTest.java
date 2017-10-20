package com.merak.lzpt.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.merak.lzpt.AppConfig;
import com.merak.lzpt.service.dto.Demo;
import com.merak.lzpt.service.inft.DemoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
public class DemoServiceTest {

	@Autowired
	DemoService demoService;

	@Test
	public void getDemo() {
		Demo demo = demoService.getDemo(1L);
		Assert.assertEquals(1L, demo.getId().longValue());
	}

	@Test
	public void getDemo2() {
		Demo demo = demoService.getDemo2(1L);
		Assert.assertEquals(1L, demo.getId().longValue());
	}
}
