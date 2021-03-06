package com.zebra.utils.exceptions;

import com.zebra.utils.StringUtil;

/**
 *HTTP异常
 * @author xiaoleilu
 */
public class HttpException extends RuntimeException{
	private static final long serialVersionUID = 8247610319171014183L;

	public HttpException(Throwable e) {
		super(e.getMessage(), e);
	}
	
	public HttpException(String message) {
		super(message);
	}
	
	public HttpException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}
	
	public HttpException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public HttpException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}
}
