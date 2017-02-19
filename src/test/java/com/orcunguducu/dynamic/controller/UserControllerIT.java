package com.orcunguducu.dynamic.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.orcunguducu.dynamic.Application;
import com.orcunguducu.dynamic.domain.Address;
import com.orcunguducu.dynamic.domain.User;

@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserControllerIT extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void createUser() throws Exception {
		String url = "/user/create";
		Gson gson = new Gson();
		String json = "";
		
		User user = new User();
		json = gson.toJson(user);
		
		/**
		 * case: create invalid user
		 * expected result: 400 (Bad Request)
		 */
		this.mvc.perform(MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		
		user.setFirstName("Test");
		user.setLastName("Test2");
		user.setUsername("test@hotmail.com");
		user.setPassword("password");
		Address address = new Address();
		address.setCity("Test");
		address.setCountry("Test");
		address.setStreet("Test");
		address.setZipCode("Test");
		List<Address> addressList = new ArrayList<>();
		addressList.add(address);
		user.setAddress(addressList);
		
		json = gson.toJson(user);
		
		/**
		 * case: create user
		 * expected result: success
		 */
		this.mvc.perform(MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());
		
		/**
		 * case: create user with same email address
		 * expected result: conflict
		 */
		this.mvc.perform(MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isConflict());
	}
}
