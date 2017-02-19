package com.orcunguducu.dynamic.service;

import java.net.URISyntaxException;
import java.util.List;

import com.orcunguducu.dynamic.domain.QueryResult;

public interface QueryService {
	public QueryResult getHistoricalQueryResult(QueryResult queryResult) throws URISyntaxException;
	public List<QueryResult> getLiveQueryResult() throws URISyntaxException;
	public List<QueryResult> getQueryResults();
}
