package com.orcunguducu.dynamic.service;

import java.util.List;

import com.orcunguducu.dynamic.domain.ParameterValue;

public interface ParameterService {
	public List<ParameterValue> getParameterValue(String parameterName);
}
