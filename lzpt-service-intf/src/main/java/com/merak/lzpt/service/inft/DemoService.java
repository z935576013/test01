package com.merak.lzpt.service.inft;

import com.merak.lzpt.service.dto.Demo;

public interface DemoService {

	Demo getDemo(Long id);
	
	Demo getDemo2(Long id);
	
	void deleteDemo(Long id);

	String saveFile(String filePath);

}
