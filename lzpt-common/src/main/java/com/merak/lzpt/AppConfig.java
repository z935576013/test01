package com.merak.lzpt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@SpringBootApplication
@MapperScan("com.merak.lzpt.mapper")
@EnableTransactionManagement
@EnableAsync
@EnableCaching
public class AppConfig {

}