package com.orcunguducu.dynamic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PARAMETER_VALUE",uniqueConstraints={
		   @UniqueConstraint(columnNames={"PARAMETER_ID","PARAM_KEY","FILTER_KEY"})})
public class ParameterValue extends BaseDomain{
	@NotNull
	@Column(name="PARAMETER_ID")
	private long parameterId;
	
	@NotNull
	@Column(name="PARAM_KEY")
	private int paramKey;
	
	@Column(name="FILTER_KEY")
	private long filterKey;
	
	@NotNull
	private String value;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PARAMETER_ID", foreignKey = @ForeignKey(name = "PARAMETER_FK"), referencedColumnName = "ID", insertable = false, updatable = false)
	private Parameter parameterObj;
	
	public long getParameterId() {
		return parameterId;
	}
	public void setParameterId(long parameterId) {
		this.parameterId = parameterId;
	}
	public int getParamKey() {
		return paramKey;
	}
	public void setParamKey(int paramKey) {
		this.paramKey = paramKey;
	}
	public long getFilterKey() {
		return filterKey;
	}
	public void setFilterKey(long filterKey) {
		this.filterKey = filterKey;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Parameter getParameterObj() {
		return parameterObj;
	}
	public void setParameterObj(Parameter parameterObj) {
		this.parameterObj = parameterObj;
	}
}
