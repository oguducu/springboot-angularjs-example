package com.orcunguducu.dynamic.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.orcunguducu.dynamic.domain.UserSettings;
import com.orcunguducu.dynamic.service.UserSettingsService;

public class UserSettingsControllerTest {
	
	@InjectMocks
	private UserSettingsController userSettingsController;
	
	@Mock
	private UserSettingsService userSettingsService;
	
	@Mock
	private BindingResult bindingResult;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveSettings() {
		UserSettings userSettings = new UserSettings();
		userSettings.setHomePageWidgets("VALID");
		Mockito.when(bindingResult.hasErrors()).thenReturn(true);
		assertEquals(userSettingsController.saveSettings(userSettings, bindingResult)
					.getStatusCode(),HttpStatus.BAD_REQUEST);
		
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		assertEquals(userSettingsController.saveSettings(userSettings, bindingResult)
					.getStatusCode(),HttpStatus.CREATED);
	}
	
	@Test
	public void getSettings() {
		Mockito.when(userSettingsService.getSettings()).thenReturn(null);
		assertEquals(userSettingsController.getSettings()
					.getStatusCode(),HttpStatus.NO_CONTENT);
		
		Mockito.when(userSettingsService.getSettings()).thenReturn(new UserSettings());
		assertEquals(userSettingsController.getSettings()
					.getStatusCode(),HttpStatus.OK);
	}
}
