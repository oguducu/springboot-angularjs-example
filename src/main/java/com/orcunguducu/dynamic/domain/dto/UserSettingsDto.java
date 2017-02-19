package com.orcunguducu.dynamic.domain.dto;

import com.orcunguducu.dynamic.domain.UserSettings;

public class UserSettingsDto {
    private String homePageWidgets;
    
    public UserSettingsDto(UserSettings userSettings) {
    	homePageWidgets = userSettings.getHomePageWidgets();
    }

	public String getHomePageWidgets() {
		return homePageWidgets;
	}

	public void setHomePageWidgets(String homePageWidgets) {
		this.homePageWidgets = homePageWidgets;
	}
}
