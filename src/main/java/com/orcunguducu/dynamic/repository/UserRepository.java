package com.orcunguducu.dynamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orcunguducu.dynamic.domain.User;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long>{
	public User getByUsername(String userName);
}
