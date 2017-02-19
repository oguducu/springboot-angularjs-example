package com.orcunguducu.dynamic.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcunguducu.dynamic.domain.QueryResult;
import com.orcunguducu.dynamic.domain.dto.QueryResultDto;
import com.orcunguducu.dynamic.service.QueryService;

/**
 * REST controller for managing queries.
 */
@RestController
@RequestMapping(value="/query")
public class QueryController  {
	@Autowired
	private QueryService queryService;
	
	/**
     * GET /getHistorical : get historical currency rate
     *
     * @param queryResult QueryResult object which contains 
     * 					  baseCurrency, quoteCurrency, date values
     * @return the ResponseEntity with status 200 (OK) and with body of QueryResult 
     *         or with status 400 (Bad Request) if QueryResult is not valid
     * @throws URISyntaxException when a string could not be parsed as a URI reference.
     */
	@GetMapping(value = "/getHistorical")
	public ResponseEntity getHistoricalQueryResult(@Valid QueryResult queryResult,BindingResult bindingResult) throws URISyntaxException{
		if(bindingResult.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
		
		QueryResult updatedQueryResult = queryService.getHistoricalQueryResult(queryResult);
		return ResponseEntity.status(HttpStatus.OK).body(new QueryResultDto(updatedQueryResult));
	}
	
	/**
     * GET /getLive : get current currency rate
     *
     * @return the ResponseEntity with status 200 (OK) and 
     *         with body of QueryResult 
     * @throws URISyntaxException when a string could not be parsed as a URI reference.
     * @throws UnsupportedEncodingException when the Character Encoding is not supported in URI
     */
	@GetMapping(value = "/getLive")
	public ResponseEntity getLiveQueryResult() throws URISyntaxException, UnsupportedEncodingException{
		List<QueryResult> queryResults = queryService.getLiveQueryResult();
		List<QueryResultDto> queryResultDtos = queryResults.stream().map(QueryResultDto::new).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(queryResultDtos);
	}
	
	/**
     * GET /getQueries : get last 10 query result which is searched by user
     *
     * @return the ResponseEntity with status 200 (OK) and 
     *         with body of List<QueryResult> 
     */
	@GetMapping(value = "/getQueries")
	public ResponseEntity getQueryResults() throws URISyntaxException{
		List<QueryResult> updatedQuery = queryService.getQueryResults();
		List<QueryResultDto> queryResultDtos = updatedQuery.stream().map(QueryResultDto::new).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(queryResultDtos);
	}
}
