package com.orcunguducu.dynamic.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.orcunguducu.dynamic.domain.Parameter;
import com.orcunguducu.dynamic.domain.ParameterValue;
import com.orcunguducu.dynamic.repository.ParameterRepository;

/**
 * Service class for managing parameters.
 */
@Service
public class ParameterServiceImpl implements ParameterService{
	
	private static final Logger LOG = LoggerFactory
			.getLogger(ParameterServiceImpl.class);
	
	@Autowired
	private ParameterRepository parameterRepository;

	@Cacheable(value="parameterCache", key="#parameterName")
	@Override
	public List<ParameterValue> getParameterValue(String parameterName) {
		LOG.info(parameterName+" parameter is cached");
		Parameter parameter = parameterRepository.getParameterByParameterName(parameterName);
		if(parameter==null)
			return new ArrayList<>();
		return parameter.getParameterValue();
	}
}
