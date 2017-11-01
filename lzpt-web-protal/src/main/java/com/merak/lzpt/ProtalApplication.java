package com.merak.lzpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ServletComponentScan
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 36000)
@PropertySource(value = "classpath:/web.properties")
public class ProtalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtalApplication.class, args);
	}

//	@Bean
//	public static ConfigureRedisAction configureRedisAction() {
//		return ConfigureRedisAction.NO_OP;
//	}
}
