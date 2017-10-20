/*
 * FileName: ImageCompressUtil.java
 * Author:   zhuliang
 * Date:     2013-9-27 上午00:00:00
 */
package com.merak.lzpt.util.file;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.imageio.ImageIO;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * 
 * 图片压缩工具
 * 
 * @author zhuliang
 */
public class ImageCompressUtil {

	/**
	 * 
	 * 图片压缩
	 * 
	 * @param in
	 *            文件流
	 * @param outputWidth
	 *            要求的宽
	 * @param outputHeight
	 *            要求的高
	 * @param proportion
	 *            是否等比压缩
	 * @param logo
	 *            水印图片
	 * @param markText
	 *            水印文字
	 * @return File 压缩后的文件
	 */
	public static File compressPic(InputStream in, int outputWidth,
			int outputHeight, boolean proportion, InputStream logo,
			String markText) {
		// 透明度
		float alpha = 0.4f;
		int fontSize = 12;
		try {
			File file = File.createTempFile("image", null);
			BufferedImage img = ImageIO.read(in);
			int newWidth = 0;
			int newHeight = 0;
			// 判断是否是等比缩放
			if (proportion) {
				// 为等比缩放计算输出的图片宽度及高度
				double rate1 = ((double) img.getWidth()) / (double) outputWidth;
				double rate2 = ((double) img.getHeight())
						/ (double) outputHeight;
				// 根据缩放比率大的进行缩放控制
				double rate = rate1 > rate2 ? rate1 : rate2;
				newWidth = (int) (((double) img.getWidth(null)) / rate);
				newHeight = (int) (((double) img.getHeight(null)) / rate);
			} else {
				newWidth = outputWidth; // 输出的图片宽度
				newHeight = outputHeight; // 输出的图片高度
			}
			BufferedImage tag = new BufferedImage((int) newWidth,
					(int) newHeight, BufferedImage.TYPE_INT_RGB);

			Graphics2D graphics = tag.createGraphics();
			graphics.drawImage(img.getScaledInstance(newWidth, newHeight,
					Image.SCALE_SMOOTH), 0, 0, null);

			if (logo != null) {
				Image src_biao = ImageIO.read(logo);
				int wideth_biao = src_biao.getWidth(null);
				int height_biao = src_biao.getHeight(null);
				graphics.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_ATOP, alpha));
				graphics.drawImage(src_biao, (newWidth - wideth_biao) / 2,
						(newHeight - height_biao) / 2, wideth_biao,
						height_biao, null);
			}
			if (markText != null) {
				AttributedString ats = new AttributedString(markText);
				Font font = new Font("宋体", Font.BOLD, fontSize);
				graphics.setFont(font);
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				ats.addAttribute(TextAttribute.FONT, font, 0, markText.length());
				AttributedCharacterIterator iter = ats.getIterator();
				/* 添加水印的文字和设置水印文字出现的内容 ----位置 */
				graphics.setComposite(AlphaComposite.getInstance(
						AlphaComposite.SRC_ATOP, alpha));
				graphics.drawString(iter, 5, newHeight - 10);
			}

			graphics.dispose();
			
			ImageIO.write(tag, "JPEG", file);
			
			return file;
		} catch (Exception ex) {
			throw new RuntimeException("compress failed", ex);
		}
	}

	/**
	 * 
	 * 图片压缩
	 * 
	 * @param in
	 *            文件流
	 * @param outputWidth
	 *            要求的宽
	 * @param outputHeight
	 *            要求的高
	 * @param proportion
	 *            是否等比压缩
	 * @return File 压缩后的文件
	 */
	public static File compressPic(InputStream in, int outputWidth,
			int outputHeight, boolean proportion) {
		return compressPic(in, outputWidth, outputHeight, proportion, null,
				null);
	}

	public static void main(String args[]) throws IOException {
		InputStream in = new FileInputStream("/Users/lz/Downloads/1.jpg");

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource resources = resolver.getResource("classpath:/img/logo.jpg");
		InputStream logo = resources.getInputStream();
		// InputStream logo = new FileInputStream("classpath:/img/logo.jpg");
		File file = ImageCompressUtil.compressPic(in, 500, 500, true, logo,
				"什么酒店什么房间");
		System.err.println(file.getAbsolutePath());
	}

}