package com.orcunguducu.dynamic.operation;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.orcunguducu.dynamic.domain.ParameterValue;
import com.orcunguducu.dynamic.service.ParameterService;
import com.orcunguducu.dynamic.util.RestCallUtil;

/**
 * Operation class for currency api calls
 */
@Component
public class CurrencyOperation {
	private static final Logger LOG = LoggerFactory
			.getLogger(CurrencyOperation.class);
	
	@Autowired
	private ParameterService parameterService;
	
	@Autowired
	private RestCallUtil restCallUtil;
	
	private String currencyApiUrl;
	private String currencyApiKey;
	
	@PostConstruct
	private void init() {
		currencyApiUrl = restCallUtil.getCurrencyApiUrl();
		currencyApiKey = restCallUtil.getCurrencyApiKey();
	}
	
	public JSONObject getHistoricalCurrency(Date date, String currencies) throws URISyntaxException {
		
		//Preparing URL
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
		URIBuilder b = new URIBuilder(currencyApiUrl+"historical?");
        b.addParameter("access_key", currencyApiKey);
        b.addParameter("date", dateFormat.format(date));
        b.addParameter("currencies", currencies);
        
        String url = b.build().toString();
        LOG.info("Currency API URL:"+url);
        
        //Request for historical currency api
        return callRestApi(url,"quotes");      
	}
	
	public JSONObject getLiveCurrency() throws URISyntaxException {
		List<ParameterValue> parameterValues = parameterService.getParameterValue("PRM_CURRENCY_LIST");
        StringBuilder sb = new StringBuilder();
        parameterValues.stream().forEach(item->sb.append(item.getValue()+","));

		//Preparing URL       
		URIBuilder b = new URIBuilder(currencyApiUrl+"live?");
        b.addParameter("access_key", currencyApiKey);
        b.addParameter("currencies", sb.toString());
        
        String url = b.build().toString();
        url=url.replace("%2C", ",");
        
        //Request for historical currency api
        return callRestApi(url,"quotes");      
	}
	
	private JSONObject callRestApi(String url,String key) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        final JSONObject obj = new JSONObject(result);
        return obj.getJSONObject(key);
	}
}
