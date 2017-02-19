package com.orcunguducu.dynamic.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcunguducu.dynamic.domain.ParameterValue;
import com.orcunguducu.dynamic.domain.dto.ParameterValueDto;
import com.orcunguducu.dynamic.service.ParameterService;

/**
 * REST controller for managing parameters.
 */
@RestController
@RequestMapping(value="/parameter")
public class ParameterController {
	@Autowired
	private ParameterService parameterService;
	
	/**
     * GET /getValue/:prmName : get requested PRM value
     *
     * @param prmName requested PRM name
     * @return the ResponseEntity with status 200 (OK)
     */
	@GetMapping(value="/getValue/{prmName}")
	public ResponseEntity getParameter(@PathVariable final String prmName) {
		List<ParameterValue> parameterValueList = parameterService.getParameterValue(prmName);
		if(parameterValueList.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		List<ParameterValueDto> parameterValueDtoList = parameterValueList.stream().map(ParameterValueDto::new).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(parameterValueDtoList);
	}
}
