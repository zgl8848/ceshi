package com.campus.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * @author campus
 * @date 2018年06月22日16:22:03
 * 403 授权拒绝
 */
@NoArgsConstructor
public class CustomDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomDeniedException(String message) {
		super(message);
	}

	public CustomDeniedException(Throwable cause) {
		super(cause);
	}

	public CustomDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
