package com.orcunguducu.dynamic.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orcunguducu.dynamic.domain.ParameterValue;
import com.orcunguducu.dynamic.domain.QueryResult;
import com.orcunguducu.dynamic.domain.User;
import com.orcunguducu.dynamic.domain.UserQueryResult;
import com.orcunguducu.dynamic.operation.CurrencyOperation;
import com.orcunguducu.dynamic.repository.QueryRepository;
import com.orcunguducu.dynamic.util.Constants;

@Service
public class QueryServiceImp implements QueryService{

	private static final Logger LOG = LoggerFactory
			.getLogger(QueryServiceImp.class);
	
	@Autowired
	private QueryRepository queryRepository;
	
	@Autowired
	private ParameterService parameterService;
	
	@Autowired
	private UserQueryResultService userQueryResultService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CurrencyOperation currencyOperation;

	@Transactional(rollbackFor=Exception.class)
	@Override
	public QueryResult getHistoricalQueryResult(QueryResult queryResult) throws URISyntaxException {
		
		//Get logged in user
		User currentUser = userService.getLoggedInUser();
		
		LOG.info(currentUser.getUsername()+" search -> "+queryResult.toString());
		
		//If query fetch before, it will read from db not from external api
		QueryResult historicalQueryResult = queryRepository.findByBaseCurrencyAndQuoteCurrencyAndDate(queryResult.getBaseCurrency(), queryResult.getQuoteCurrency(), queryResult.getDate());
		if(historicalQueryResult!=null) {
			UserQueryResult userQueryResult = new UserQueryResult();
	        userQueryResult.setUser(currentUser);
	        userQueryResult.setQueryResult(historicalQueryResult);
	        userQueryResultService.saveUserQueryResult(userQueryResult);
			return historicalQueryResult;
		}
		
        final JSONObject quotes = currencyOperation.getHistoricalCurrency(
        		queryResult.getDate(),queryResult.getQuoteCurrency());
        
        //Saving query to database
        String currencyQuote = queryResult.getBaseCurrency()+queryResult.getQuoteCurrency();
        queryResult.setRate(quotes.getDouble(currencyQuote));
        queryRepository.save(queryResult);
        
        UserQueryResult userQueryResult = new UserQueryResult();
        userQueryResult.setUser(currentUser);
        userQueryResult.setQueryResult(queryResult);
        userQueryResultService.saveUserQueryResult(userQueryResult);
        
		return queryResult;
	}

	@Override
	public List<QueryResult> getQueryResults() {
		User currentUser = userService.getLoggedInUser();
		return queryRepository.queryResultsByUserId(currentUser.getId(),new PageRequest(0, 10));
	}

	@Override
	public List<QueryResult> getLiveQueryResult()
			throws URISyntaxException {
		List<ParameterValue> parameterValues = parameterService.getParameterValue("PRM_CURRENCY_LIST");
        StringBuilder sb = new StringBuilder();
        parameterValues.stream().forEach(item->sb.append(item.getValue()+","));
        
        final JSONObject quotes = currencyOperation.getLiveCurrency();

        List<QueryResult> queryResults = new ArrayList<>();
        for(ParameterValue parameterValue:parameterValues) {
        	/* Because free version api is not allowing to change base currency, 
        	 * It has been set manually
        	 */
        	String baseCurrency = Constants.CURRENCY_USD;
        	String quoteCurrency = parameterValue.getValue();
        	if(baseCurrency.equals(quoteCurrency))
        		continue;
        	QueryResult queryResult = new QueryResult();
        	double rate = quotes.getDouble(baseCurrency+quoteCurrency);
        	
        	queryResult.setBaseCurrency(baseCurrency);
        	queryResult.setQuoteCurrency(quoteCurrency);
        	queryResult.setRate(rate);
        	queryResults.add(queryResult);
        }
        
		return queryResults;
	}
}
