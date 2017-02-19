package com.orcunguducu.dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcunguducu.dynamic.domain.UserSettings;
import com.orcunguducu.dynamic.repository.UserSettingsRepository;

/**
 * Service class for managing user settings.
 */
@Service
public class UserSettingsServiceImpl implements UserSettingsService{
	
	@Autowired
	private UserSettingsRepository userSettingsRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void saveSettings(UserSettings userSettings) {
		long userId = userService.getLoggedInUser().getId();
		userSettings.setUserId(userId);
		userSettingsRepository.save(userSettings);
	}

	@Override
	public UserSettings getSettings() {
		long userId = userService.getLoggedInUser().getId();
		return userSettingsRepository.getUserSettingsByUserId(userId);
	}
}
