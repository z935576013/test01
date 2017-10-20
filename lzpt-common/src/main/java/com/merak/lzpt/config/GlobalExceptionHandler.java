package com.merak.lzpt.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.merak.lzpt.util.ErrorMessage;
import com.merak.lzpt.util.ResultBean;

@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public Object defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		logger.error("defaultErrorHandler", e);
		return new ResultBean(ErrorMessage.ERROR_0000);
	}

}
