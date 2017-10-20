package com.merak.lzpt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * @author zhuliang
 */
@ConfigurationProperties(prefix = "file.disk")
@Component
public class DiskConfig {
	private String filePath;

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
