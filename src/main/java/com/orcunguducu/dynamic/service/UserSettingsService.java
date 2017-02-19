package com.orcunguducu.dynamic.service;

import com.orcunguducu.dynamic.domain.UserSettings;

public interface UserSettingsService {
	public void saveSettings(UserSettings userSettings);
	public UserSettings getSettings();
}
