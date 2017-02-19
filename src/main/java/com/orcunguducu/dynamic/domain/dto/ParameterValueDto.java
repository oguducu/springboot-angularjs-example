package com.orcunguducu.dynamic.domain.dto;

import com.orcunguducu.dynamic.domain.ParameterValue;

public class ParameterValueDto {
	private int paramKey;
	private long filterKey;
	private String value;
	
	public ParameterValueDto(ParameterValue parameterValue) {
		paramKey = parameterValue.getParamKey();
		filterKey = parameterValue.getFilterKey();
		value = parameterValue.getValue();
	}
	
	public int getParamKey() {
		return paramKey;
	}
	public void setParamKey(int paramKey) {
		this.paramKey = paramKey;
	}
	public long getFilterKey() {
		return filterKey;
	}
	public void setFilterKey(long filterKey) {
		this.filterKey = filterKey;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
