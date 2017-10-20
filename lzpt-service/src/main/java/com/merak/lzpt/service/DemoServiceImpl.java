package com.merak.lzpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.merak.lzpt.config.FtpConfig;
import com.merak.lzpt.mapper.DemoMapper;
import com.merak.lzpt.service.dto.Demo;
import com.merak.lzpt.service.inft.DemoService;
import com.merak.lzpt.util.file.FileClient;

@Service
@CacheConfig(cacheNames = "DemoService")
public class DemoServiceImpl implements DemoService {

	@Autowired
	DemoMapper demoMapper;

	@Autowired
	AsyncTask asyncTask;

	@Autowired
	FtpConfig ftpConfig;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	FileClient fileClient;

	@Override
	@Transactional
	public Demo getDemo(Long id) {
		Demo demo = demoMapper.selectByPrimaryKey(id);
		stringRedisTemplate.opsForValue().set("temp1", JSON.toJSONString(demo));
		String str = stringRedisTemplate.opsForValue().get("temp1");
		demo = JSON.parseObject(str, Demo.class);
		asyncTask.doTask1();
		return demo;
	}

	@Override
	@Cacheable(key = "'getDemo2_'+#id")
	public Demo getDemo2(Long id) {
		Demo demo = demoMapper.selectByPrimaryKey(id);
		return demo;
	}

	@CacheEvict(key = "'getDemo2_'+#id")
	public void deleteDemo(Long id) {
		
	}

	
	
	@Override
	public String saveFile(String filePath) {
		String path = fileClient.saveFile(filePath, FileClient.BIZTYPE_HOTEL_PIC);
		return path;
	}

	
}
