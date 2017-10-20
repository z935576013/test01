package com.merak.lzpt.util.file;

public interface FileClient {

	/**
	 * 业务类型：酒店图片
	 */
	public static String BIZTYPE_HOTEL_PIC = "hotel";

	/**
	 * 
	 * 保存一个本地文件，返回文件path，失败返回null
	 * 
	 * @param localFileName
	 *            本地文件名
	 * @param bizType
	 *            业务类型
	 * @param asyn
	 *            是否异步 ，默认同步
	 * @param suffix
	 *            文件后缀， 默认.jpg
	 * @param mark
	 *            水印文字
	 * @return 文件path
	 */
	String saveFile(String localFileName, String bizType, String suffix, String mark);

	/**
	 * 
	 * 保存一个本地文件，返回文件path，失败返回null,默认同步，.jpg后缀
	 * 
	 * @param localFileName
	 *            本地文件名
	 * @param bizType
	 *            业务类型
	 * @return 文件path
	 */
	String saveFile(String localFileName, String bizType);

}
