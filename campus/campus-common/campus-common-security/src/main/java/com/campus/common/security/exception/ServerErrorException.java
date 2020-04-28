package com.campus.common.security.exception;

import com.campus.common.security.component.CustomAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;

/**
 * @author campus
 * @date 2018/7/8
 */
@JsonSerialize(using = CustomAuth2ExceptionSerializer.class)
public class ServerErrorException extends CustomAuth2Exception {

	public ServerErrorException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "server_error";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

}
