package com.orcunguducu.dynamic.service;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.orcunguducu.dynamic.domain.QueryResult;
import com.orcunguducu.dynamic.domain.User;
import com.orcunguducu.dynamic.repository.QueryRepository;

public class QueryServiceImplTest {
	@Mock
	private UserService userService;
	
	@Mock
	private UserQueryResultService userQueryResultService;
	
	@Mock
	private QueryRepository queryRepository;
	
	@InjectMocks
	private QueryServiceImp queryService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		User user = new User();
		user.setUsername("test@test.com");
		Mockito.when(userService.getLoggedInUser()).thenReturn(user);
	}
	
	@Test
	public void saveQueryResult() throws Exception {
		QueryResult queryResult = new QueryResult();
		queryResult.setBaseCurrency("USD");
		queryResult.setQuoteCurrency("USD");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("10/12/2016");
		queryResult.setDate(date);
		
		Mockito.when(queryRepository.findByBaseCurrencyAndQuoteCurrencyAndDate(queryResult.getBaseCurrency(),queryResult.getQuoteCurrency(),queryResult.getDate())).thenReturn(null);
		Mockito.when(userQueryResultService.saveUserQueryResult(Mockito.any())).thenReturn(null);
		/**
		 * Getting rate value from external service (also integration test)
	     * @return QueryResult filled rate value
	     */
		QueryResult updatedQueryResult = queryService.getHistoricalQueryResult(queryResult);
		assertTrue(updatedQueryResult.getRate()==1);
		
		
		queryResult.setRate(1);
		Mockito.when(queryRepository.findByBaseCurrencyAndQuoteCurrencyAndDate(queryResult.getBaseCurrency(),queryResult.getQuoteCurrency(),queryResult.getDate())).thenReturn(queryResult);
		/**
		 * Getting rate from db (because it was fetched before)
	     * @return QueryResult filled rate value
	     */
		updatedQueryResult = queryService.getHistoricalQueryResult(queryResult);
		assertTrue(updatedQueryResult.getRate()==1);
	}
}
