package com.campus.common.security.exception;

import com.campus.common.security.component.CustomAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * @author campus
 * @date 2018/7/8
 */
@JsonSerialize(using = CustomAuth2ExceptionSerializer.class)
public class ForbiddenException extends CustomAuth2Exception {

	public ForbiddenException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "access_denied";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.FORBIDDEN.value();
	}

}

