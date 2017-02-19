package com.orcunguducu.dynamic.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.orcunguducu.dynamic.domain.Parameter;
import com.orcunguducu.dynamic.domain.ParameterValue;
import com.orcunguducu.dynamic.repository.ParameterRepository;

public class ParameterServiceImplTest {
	@Mock
	private ParameterRepository parameterRepository;
	
	@InjectMocks
	private ParameterServiceImpl parameterService;
	
	private final String correctParameterName = "PRM_COUNTRY_LIST";
	private final String wrongParameterName = "NO_PRM";
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Parameter parameter = new Parameter();
		List<ParameterValue> parameterValues = new ArrayList<>();
		ParameterValue parameterValue = new ParameterValue();
		parameterValue.setValue("MyParameter");
		parameterValue.setFilterKey(1);
		parameterValue.setParamKey(1);
		parameterValue.setParameterId(1);
		parameterValues.add(parameterValue);
		parameter.setParameterValue(parameterValues);
		
		Mockito.when(parameterRepository.getParameterByParameterName(correctParameterName)).thenReturn(parameter);
		
		Mockito.when(parameterRepository.getParameterByParameterName(wrongParameterName)).thenReturn(null);
	}
	
	@Test
	public void getParameterResult() {
		/**
	     * @param prmName exists prm name
	     * @return List<ParameterValue> filled with paramterValues
	     */
		List<ParameterValue> parameterValues = parameterService.getParameterValue(correctParameterName);
		assertFalse(parameterValues.isEmpty());
		
		/**
	     * @param prmName non exists prm name
	     * @return empty List<ParameterValue> 
	     */
		List<ParameterValue> emptyParameterValues = parameterService.getParameterValue(wrongParameterName);
		assertTrue(emptyParameterValues.isEmpty());
	}
}
