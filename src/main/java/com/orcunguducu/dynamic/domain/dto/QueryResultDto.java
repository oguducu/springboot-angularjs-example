package com.orcunguducu.dynamic.domain.dto;

import java.util.Date;

import com.orcunguducu.dynamic.domain.QueryResult;

public class QueryResultDto {
	private Date date;
	private String baseCurrency;
	private String quoteCurrency;
	private double rate;
	
	public QueryResultDto(QueryResult queryResult) {
		date = queryResult.getDate();
		baseCurrency = queryResult.getBaseCurrency();
		quoteCurrency = queryResult.getQuoteCurrency();
		rate = queryResult.getRate();
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public String getQuoteCurrency() {
		return quoteCurrency;
	}
	public void setQuoteCurrency(String quoteCurrency) {
		this.quoteCurrency = quoteCurrency;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
}
