package com.orcunguducu.dynamic.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "base.url")
@Component
public class RestCallUtil {
	private String currencyApiUrl;
	private String currencyApiKey;

	public String getCurrencyApiUrl() {
		return currencyApiUrl;
	}

	public void setCurrencyApiUrl(String currencyApiUrl) {
		this.currencyApiUrl = currencyApiUrl;
	}

	public String getCurrencyApiKey() {
		return currencyApiKey;
	}

	public void setCurrencyApiKey(String currencyApiKey) {
		this.currencyApiKey = currencyApiKey;
	}
}
