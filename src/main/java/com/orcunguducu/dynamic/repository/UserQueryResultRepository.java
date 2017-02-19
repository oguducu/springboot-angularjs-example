package com.orcunguducu.dynamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orcunguducu.dynamic.domain.UserQueryResult;

/**
 * Spring Data JPA repository for the UserQueryResult entity.
 */
public interface UserQueryResultRepository extends JpaRepository<UserQueryResult, Long>{
	
}
