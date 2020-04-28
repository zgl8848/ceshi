package com.campus.grid.api.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JPush {
	@Value("${jPush.appKey}")
	private String appKey;

	@Value("${jPush.masterSecret}")
	private String masterSecret;

	public JPush() {
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getMasterSecret() {
		return masterSecret;
	}

	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}

	@Override
	public String toString() {
		return "JPush{" +
				"appKey='" + appKey + '\'' +
				", masterSecret='" + masterSecret + '\'' +
				'}';
	}
}
