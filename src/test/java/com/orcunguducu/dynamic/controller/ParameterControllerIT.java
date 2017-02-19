package com.orcunguducu.dynamic.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.orcunguducu.dynamic.Application;

/**
 * Integratation test for parameters
 * 
 * @author Orcun GUducu
 *
 */

@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ParameterControllerIT {
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;
	
	private final String url = "/parameter/getValue/";
	private final String correctParameterName = "PRM_COUNTRY_LIST";
	private final String incorrectParameterName = "INCORRECT_PARAMETER_NAME";

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void getParameterResult() throws Exception {
		
		/**
		 * case: get prm with exist prm name
		 * expected result: Status: 200 - HttpStatus.OK
		 */
		this.mvc.perform(MockMvcRequestBuilders.get(url+correctParameterName))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		/**
		 * case: get prm with non exists prm name
		 * expected result: Status: 204 - HttpStatus.NO_CONTENT
		 */
		this.mvc.perform(MockMvcRequestBuilders.get(url+incorrectParameterName))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
