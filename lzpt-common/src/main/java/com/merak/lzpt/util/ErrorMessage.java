package com.merak.lzpt.util;

/**
 * 错误消息常量
 *
 * @author zhuliang
 */
public enum ErrorMessage {
	
	//公共异常信息
	ERROR_0000("0000", "系统内部错误", "System internal error"),
	ERROR_0001("0001", "需要登陆", "Need Login"),
	ERROR_0002("0002", "需要管理员登陆", "Need Admin Login"),
	ERROR_0003("0003", "参数类型不正确", "Bad Request");
	
	/**
	 * 错误编码
	 */
	private String errorCode;
	/**
	 * 错误描述
	 */
	private String errorMessage;

	private String errorMessgaeEn;

	/**
	 * 
	 * @param errorCode
	 * @param errorMessage
	 */
	private ErrorMessage(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	private ErrorMessage(String errorCode, String errorMessage, String errorMessageEn) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorMessgaeEn = errorMessageEn;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorMessgaeEn() {
		return errorMessgaeEn;
	}
}
