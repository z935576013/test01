package com.merak.lzpt.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 成功标示 T成功
	 */
	public static String SUCCESS = "T";
	/**
	 * 成功标示F失败
	 */
	public static String FAILURE = "F";
	/**
	 * 成功标示 T成功；F失败
	 */
	public String isSuccess = SUCCESS;

	/**
	 * 错误编码
	 */
	@JsonInclude(Include.NON_NULL)
	private String errorCode = null;
	/**
	 * 错误描述
	 */
	@JsonInclude(Include.NON_NULL)
	private String errorMsg = null;

	@JsonInclude(Include.NON_NULL)
	private String errorMsgEn = null;

	/**
	 * 返回报文体
	 */
	@JsonInclude(Include.NON_NULL)
	private Map<String, Object> data = null;

	/**
	 * 
	 */
	public ResultBean() {
	}

	/**
	*
	*/
	public ResultBean(ErrorMessage errorMessage) {
		isSuccess = FAILURE;
		errorCode = errorMessage.getErrorCode();
		errorMsg = errorMessage.getErrorMessage();
		errorMsgEn = errorMessage.getErrorMessgaeEn();
	}

	/**
	*
	*/
	public ResultBean(ErrorMessage errorMessage, String param) {
		isSuccess = FAILURE;
		errorCode = errorMessage.getErrorCode();
		errorMsg = errorMessage.getErrorMessage();
		errorMsgEn = errorMessage.getErrorMessgaeEn();
	}

	/**
	 * 
	 * @param message
	 * @param param
	 * @return
	 */
	public String replaceParam(String message, String param) {
		return message.replace("?", param);
	}

	/**
	 * 
	 */
	public ResultBean(String errorCode, String errorMsg) {
		isSuccess = FAILURE;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public ResultBean(String errorCode, String errorMsg, String errorMsgEn) {
		isSuccess = FAILURE;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorMsgEn = errorMsgEn;
	}

	/**
	 * 
	 */
	public ResultBean addAttribute(String key, Object value) {
		if (data == null) {
			data = new HashMap<String, Object>();
		}
		data.put(key, value);
		return this;
	}

	/**
	 * @return the data
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the errorMsgEn
	 */
	public String getErrorMsgEn() {
		return errorMsgEn;
	}

	/**
	 * @param errorMsgEn
	 *            the errorMsgEn to set
	 */
	public void setErrorMsgEn(String errorMsgEn) {
		this.errorMsgEn = errorMsgEn;
	}

}
