package com.campus.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.campus.common.core.constant.CommonConstants;
import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.core.exception.ValidateCodeException;
import com.campus.common.core.util.R;
import com.campus.common.core.util.WebUtils;
import com.campus.gateway.config.FilterIgnorePropertiesConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author campus
 * @date 2018/7/4
 * 验证码处理
 */
@Slf4j
@Component
@AllArgsConstructor
public class ValidateCodeGatewayFilter extends AbstractGatewayFilterFactory {
	private final ObjectMapper objectMapper;
	private final RedisTemplate redisTemplate;
	private final FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();

			// 不是登录请求，直接向下执行
			if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath()
					, SecurityConstants.OAUTH_TOKEN_URL, SecurityConstants.SMS_TOKEN_URL)) {
				return chain.filter(exchange);
			}

			// 刷新token，直接向下执行
			String grantType = request.getQueryParams().getFirst("grant_type");
			if (StrUtil.equals(SecurityConstants.REFRESH_TOKEN, grantType)) {
				return chain.filter(exchange);
			}

			// 终端设置不校验， 直接向下执行
			try {
				String[] clientInfos = WebUtils.getClientId(request);
				if (filterIgnorePropertiesConfig.getClients().contains(clientInfos[0])) {
					return chain.filter(exchange);
				}

				//校验验证码
				checkCode(request);
			} catch (Exception e) {
				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode(HttpStatus.PRECONDITION_REQUIRED);
				try {
					return response.writeWith(Mono.just(response.bufferFactory()
							.wrap(objectMapper.writeValueAsBytes(
									R.builder().msg(e.getMessage())
											.code(CommonConstants.FAIL).build()))));
				} catch (JsonProcessingException e1) {
					log.error("对象输出异常", e1);
				}
			}

			return chain.filter(exchange);
		};
	}

	/**
	 * 检查code
	 *
	 * @param request
	 * @throws ValidateCodeException 校验异常
	 */
	@SneakyThrows
	private void checkCode(ServerHttpRequest request) {
		String code = request.getQueryParams().getFirst("code");

		if (StrUtil.isBlank(code)) {
			throw new ValidateCodeException("验证码不能为空");
		}

		String randomStr = request.getQueryParams().getFirst("randomStr");
		if (StrUtil.isBlank(randomStr)) {
			randomStr = request.getQueryParams().getFirst("mobile");
		}

		String key = CommonConstants.DEFAULT_CODE_KEY + randomStr;
		// redisTemplate使用jdk序列化，系统在初次生成验证码的时候前面会加入一些不知道的东西，不知道为什么
		// 类似这种 \xAC\xED\x00\x05t\x00"DEFAULT_CODE_KEY_18561551322772953
		// 如果用换成jackson序列化就好使了
		if (!redisTemplate.hasKey(key)) {
			throw new ValidateCodeException("验证码失效了");
		}

		Object codeObj = redisTemplate.opsForValue().get(key);

		if (codeObj == null) {
			throw new ValidateCodeException("验证码失效了");
		}

		String saveCode = codeObj.toString();
		if (StrUtil.isBlank(saveCode)) {
			redisTemplate.delete(key);
			throw new ValidateCodeException("验证码失效了");
		}

		if (!StrUtil.equals(saveCode, code)) {
			redisTemplate.delete(key);
			throw new ValidateCodeException("验证码不正确");
		}

		redisTemplate.delete(key);
	}
}
