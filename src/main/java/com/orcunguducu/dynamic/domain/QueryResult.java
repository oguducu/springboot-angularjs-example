package com.orcunguducu.dynamic.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="QUERY_RESULT")
public class QueryResult extends BaseDomain{
	@NotNull
	@DateTimeFormat(pattern="dd.MM.yyyy")
	@Temporal(TemporalType.DATE)
	private Date date;

	@NotNull
	@Column(name="BASE_CURRENCY")
	private String baseCurrency;
	
	@NotNull
	@Column(name="QUOTE_CURRENCY")
	private String quoteCurrency;
	
	private double rate;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="queryResult", fetch = FetchType.LAZY )   
    private List<UserQueryResult>  userQueryResults;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public String getQuoteCurrency() {
		return quoteCurrency;
	}
	public void setQuoteCurrency(String quoteCurrency) {
		this.quoteCurrency = quoteCurrency;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public List<UserQueryResult> getUserQueryResults() {
		return userQueryResults;
	}
	public void setUserQueryResults(List<UserQueryResult> userQueryResults) {
		this.userQueryResults = userQueryResults;
	}
	
	@Override
	public String toString() {
		return "baseCurrency="+baseCurrency+" - quoteCurrency="+quoteCurrency+" - date="+date;
	}
}
