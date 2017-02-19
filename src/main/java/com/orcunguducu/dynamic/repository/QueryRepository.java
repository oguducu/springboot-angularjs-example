package com.orcunguducu.dynamic.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.orcunguducu.dynamic.domain.QueryResult;

/**
 * Spring Data JPA repository for the Query entity.
 */
public interface QueryRepository extends JpaRepository<QueryResult, Long>{
	@Query("select q from UserQueryResult uqr LEFT JOIN uqr.user u LEFT JOIN uqr.queryResult q WHERE u.id = :userId ORDER BY uqr.insertDate desc")
	public List<QueryResult> queryResultsByUserId(@Param("userId") long userId, Pageable pageable);
	public QueryResult findByBaseCurrencyAndQuoteCurrencyAndDate(String baseCurrency,String quoteCurrency,Date date);
}
