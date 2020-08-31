package com.github.huifer.sc.consumption.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
* 
* <p>Title: FeignBean.java</p>
* @author Carlton
* @date 2019年4月29日 上午9:43:38
*/
@Component
public class FeignBean {

	@Value("${spring.test.feign.url}")
	private String url;

	@Value("${spring.test.feign.auth.name}")
	private String adminName;
	@Value("${spring.test.feign.auth.password}")
	private String adminPassword;

	@Value("${spring.test.feign.opion.connectTimeoutMillis}")
	private int opion_conn;
	@Value("${spring.test.feign.opion.readTimeoutMillis}")
	private int opion_read;

	@Value("${spring.test.feign.retry.period}")
	private long retry_period;
	@Value("${spring.test.feign.retry.maxPeriod}")
	private long retry_maxPeriod;
	@Value("${spring.test.feign.retry.maxAttempts}")
	private int retry_maxAttempts;

	public String getUrl() {
		return url;
	}

	public String getAdminName() {
		return adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public int getOpion_conn() {
		return opion_conn;
	}

	public int getOpion_read() {
		return opion_read;
	}

	public long getRetry_period() {
		return retry_period;
	}

	public long getRetry_maxPeriod() {
		return retry_maxPeriod;
	}

	public int getRetry_maxAttempts() {
		return retry_maxAttempts;
	}

}
