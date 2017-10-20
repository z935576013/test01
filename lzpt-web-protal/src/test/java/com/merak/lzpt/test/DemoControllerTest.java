package com.merak.lzpt.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.merak.lzpt.ProtalApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProtalApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void login() {
		String uri = "/login/userLogin";
		String body = this.restTemplate.getForObject(uri, String.class);
		System.out.println(body);
	}

	@Test
	public void demo1() {
		String uri = "/demo/demo1?id=1";
		String body = this.restTemplate.getForObject(uri, String.class);
		Assert.assertEquals("bb", body);
	}

}
