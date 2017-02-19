package com.orcunguducu.dynamic.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PARAMETER")
public class Parameter extends BaseDomain{
	@NotNull
	@Column(name="PARAMETER_NAME")
	private String parameterName;

	@OneToMany(mappedBy="parameterObj", fetch = FetchType.LAZY)
	private List<ParameterValue> parameterValue;
	
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public List<ParameterValue> getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(List<ParameterValue> parameterValue) {
		this.parameterValue = parameterValue;
	}
}
