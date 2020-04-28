package com.campus.common.security.exception;

import com.campus.common.security.component.CustomAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author campus
 * @date 2018/7/8
 * 自定义OAuth2Exception
 */
@JsonSerialize(using = CustomAuth2ExceptionSerializer.class)
public class CustomAuth2Exception extends OAuth2Exception {
	@Getter
	private String errorCode;

	public CustomAuth2Exception(String msg) {
		super(msg);
	}

	public CustomAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
