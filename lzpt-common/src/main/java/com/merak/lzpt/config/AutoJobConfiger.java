package com.merak.lzpt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 
 * 自动任务
 *
 * @author zhuliang
 */
@EnableScheduling
@Configuration
public class AutoJobConfiger {

	@Scheduled(cron = "0 0 * * * ?")
	public void countReturn() {
		System.out.println("AutoJob");
	}
	
}
