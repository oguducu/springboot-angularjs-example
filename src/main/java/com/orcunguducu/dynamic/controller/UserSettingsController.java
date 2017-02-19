package com.orcunguducu.dynamic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcunguducu.dynamic.domain.UserSettings;
import com.orcunguducu.dynamic.domain.dto.UserSettingsDto;
import com.orcunguducu.dynamic.service.UserSettingsService;

/**
 * REST controller for managing users' settings.
 */
@RestController
@RequestMapping(value="/userSettings")
public class UserSettingsController {
	@Autowired
	private UserSettingsService userSettingsService;
	
	/**
     * POST  : Creates a new user settings.
     * <p>
     * Creates a new user settings if it is valid
     * </p>
     *
     * @param UserSettings object which contains settings string
     * @return the ResponseEntity with status 201 (Created) 
     *         or with status 400 (Bad Request) if user settings is not valid
     */
	@PostMapping
	public ResponseEntity saveSettings(@RequestBody @Valid UserSettings userSettings,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
		
		userSettingsService.saveSettings(userSettings);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
	/**
     * GET : get current logged in user settings
     *
     * @return the ResponseEntity with status 200 (OK) and with body of UserSettingsDto 
     *         0r with status 204 (No Content) if no user settings saved before
     */
	@GetMapping
	public ResponseEntity getSettings() {
		UserSettings userSettings = userSettingsService.getSettings();
		if(userSettings==null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		return ResponseEntity.status(HttpStatus.OK).body(new UserSettingsDto(userSettings));
	}
}
