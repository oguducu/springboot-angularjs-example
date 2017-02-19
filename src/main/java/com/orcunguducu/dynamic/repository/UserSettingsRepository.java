package com.orcunguducu.dynamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orcunguducu.dynamic.domain.UserSettings;

/**
 * Spring Data JPA repository for the User Settings entity.
 */
public interface UserSettingsRepository extends JpaRepository<UserSettings, Long>{
	public UserSettings getUserSettingsByUserId(long userId);
}
