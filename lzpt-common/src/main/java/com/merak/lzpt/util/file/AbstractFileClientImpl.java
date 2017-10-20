package com.merak.lzpt.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public abstract class AbstractFileClientImpl implements FileClient {

	/**
	 * 路径分隔符
	 */
	private static final String DIR_SEPARATOR = "/";

	private static final String LOGO_PATH = "classpath:/img/logo.jpg";

	SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String saveFile(String localFileName, String bizType, String suffix, String mark) {
		String toUploadFile = localFileName;

		toUploadFile = processFile(bizType, toUploadFile, mark);

		String filePath = null;
		String uuid = getUuid();
		String path = getPath(bizType, uuid);

		boolean success = upLoad(toUploadFile, path, uuid + suffix);
		if (success) {
			filePath = path + uuid + suffix;
			return filePath;
		} else {
			throw new RuntimeException("upLoad faild");
		}
	}

	protected String processFile(String bizType, String localFileName, String mark) {
		String toUploadFile = localFileName;
		FileInputStream fi = null;
		try {
			InputStream logo = null;
			String marlText = null;
			if (needMark(bizType)) {
				ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
				Resource resources = resolver.getResource(LOGO_PATH);
				logo = resources.getInputStream();
				marlText = mark;
			}
			fi = new FileInputStream(toUploadFile);
			File file = ImageCompressUtil.compressPic(fi, 800, 800, true, logo, marlText);
			toUploadFile = file.getAbsolutePath();
		} catch (Exception e) {
			// igore
		} finally {
			try {
				if (fi != null) {
					fi.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return toUploadFile;
	}

	protected boolean needMark(String bizType) {
		if (bizType.equals(FileClient.BIZTYPE_HOTEL_PIC)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String saveFile(String localFileName, String bizType) {
		String suffix = ".jpg";
		return saveFile(localFileName, bizType, suffix, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * {@inheritDoc}
	 */
	protected String getPath(String bizType, String uuid) {
		StringBuffer buffer = new StringBuffer(uuid);
		int d = buffer.length() / 3;
		for (int i = 0; i <= d; i++) {
			buffer.insert(i * 4, DIR_SEPARATOR);
		}
		return buffer.insert(0, sdf.format(new Date())).insert(0, DIR_SEPARATOR).insert(0, bizType)
				.insert(0, DIR_SEPARATOR).append(DIR_SEPARATOR).toString();
	}

	protected abstract boolean upLoad(String localFileName, String path, String fileName);

}
