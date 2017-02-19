package com.orcunguducu.dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcunguducu.dynamic.domain.UserQueryResult;
import com.orcunguducu.dynamic.repository.UserQueryResultRepository;

/**
 * Service class for managing user query result.
 */
@Service
public class UserQueryResultServiceImpl implements UserQueryResultService{

	@Autowired
	private UserQueryResultRepository userQueryResultRepository;
	
	@Override
	public UserQueryResult saveUserQueryResult(UserQueryResult userQueryResult) {
		return userQueryResultRepository.save(userQueryResult);
	}
	
}
