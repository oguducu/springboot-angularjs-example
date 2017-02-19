package com.orcunguducu.dynamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orcunguducu.dynamic.domain.Parameter;

/**
 * Spring Data JPA repository for the Parameter entity.
 */
public interface ParameterRepository extends JpaRepository<Parameter, Long>{
	public Parameter getParameterByParameterName(String parameterName);
}
