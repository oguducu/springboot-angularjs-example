package com.orcunguducu.dynamic.controller;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.orcunguducu.dynamic.domain.QueryResult;
import com.orcunguducu.dynamic.service.QueryService;


public class QueryControllerTest {
	@InjectMocks
    private QueryController queryController;
	
	@Mock
	private QueryService queryService;
	
	@Mock
	private BindingResult bindingResult;
	
	@Before
	public void setUp() throws URISyntaxException {
		MockitoAnnotations.initMocks(this);
		
		List<QueryResult> queryResults = new ArrayList<>();
		QueryResult queryResult = new QueryResult();
		queryResult.setBaseCurrency("USD");
		queryResult.setQuoteCurrency("EUR");
		queryResult.setDate(new Date());
		queryResult.setRate(1);
		queryResults.add(queryResult);
		Mockito.when(queryService.getLiveQueryResult()).thenReturn(queryResults);
	}
	
	@Test
	public void getLiveQueryResult() throws Exception {
		
		/**
	     * @return the ResponseEntity with status 200 (OK)
	     */
		assertTrue(queryController.getLiveQueryResult().getStatusCode().equals(HttpStatus.OK));
	}
	
	@Test
	public void getHistoryQueryResult() throws Exception {
		QueryResult queryResult = new QueryResult();
		queryResult.setBaseCurrency("USD");
		queryResult.setQuoteCurrency("EUR");
		queryResult.setDate(new Date());
		Mockito.when(queryService.getHistoricalQueryResult(queryResult)).thenReturn(queryResult);
		
		/**
		 * @param queryResult 
	     * @return the ResponseEntity with status 200 (OK)
	     */
		assertTrue(queryController.getHistoricalQueryResult(queryResult, bindingResult).getStatusCode().equals(HttpStatus.OK));
		
		Mockito.when(bindingResult.hasErrors()).thenReturn(true);
		/**
		 * @param queryResult 
	     * @return the ResponseEntity with status 400 (BAD_REQUEST)
	     */
		assertTrue(queryController.getHistoricalQueryResult(queryResult, bindingResult).getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void getQueries() throws Exception {
		List<QueryResult> queries = new ArrayList<>();
		QueryResult queryResult = new QueryResult();
		queryResult.setBaseCurrency("USD");
		queryResult.setQuoteCurrency("EUR");
		queryResult.setDate(new Date());
		queries.add(queryResult);
		
		Mockito.when(queryService.getQueryResults()).thenReturn(queries);
		
		/**
	     * @return the ResponseEntity with status 200 (OK)
	     */
		assertTrue(queryController.getQueryResults().getStatusCode().equals(HttpStatus.OK));
	}
}