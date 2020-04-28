package com.campus.common.security.component;

import com.campus.common.core.constant.CommonConstants;
import com.campus.common.security.exception.CustomAuth2Exception;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author campus
 * @date 2018/11/16
 * <p>
 * OAuth2 异常格式化
 */
public class CustomAuth2ExceptionSerializer extends StdSerializer<CustomAuth2Exception> {
	public CustomAuth2ExceptionSerializer() {
		super(CustomAuth2Exception.class);
	}

	@Override
	public void serialize(CustomAuth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}
}
