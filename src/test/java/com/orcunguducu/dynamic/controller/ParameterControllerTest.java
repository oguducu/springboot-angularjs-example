package com.orcunguducu.dynamic.controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.orcunguducu.dynamic.domain.ParameterValue;
import com.orcunguducu.dynamic.service.ParameterService;

/**
 * Unit tests for parameters
 * 
 * @author Orcun Guducu
 *
 */
public class ParameterControllerTest {
	@Mock
	private ParameterService parameterService;
	
	@InjectMocks
    private ParameterController parameterController;
	
	private final String correctParameterName = "PRM_COUNTRY_LIST";
	private final String wrongParameterName = "NO_PRM";

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		List<ParameterValue> parameterValues = new ArrayList<>();
		ParameterValue parameterValue = new ParameterValue();
		parameterValue.setValue("MyParameter");
		parameterValues.add(parameterValue);
		Mockito.when(parameterService.getParameterValue(correctParameterName)).thenReturn(parameterValues);
		
		Mockito.when(parameterService.getParameterValue(wrongParameterName)).thenReturn(new ArrayList<>());
	}
	
	@Test
	public void getParameterResult() throws Exception {
		
		/**
	     * @param prmName exists prm name
	     * @return the ResponseEntity with status 200 (OK)
	     */
		assertTrue(parameterController.getParameter(correctParameterName).getStatusCode().equals(HttpStatus.OK));
		
		/**
	     * @param prmName non exists prm name
	     * @return the ResponseEntity with Status: 204 - HttpStatus.NO_CONTENT
	     */
		assertTrue(parameterController.getParameter(wrongParameterName).getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
}
