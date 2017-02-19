package com.orcunguducu.dynamic.controller;

import java.net.URISyntaxException;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcunguducu.dynamic.domain.User;
import com.orcunguducu.dynamic.service.UserService;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	/**
     * GET / : spring security controller
     *
     * @return user
     */
	@GetMapping("")
	public Principal user(Principal user) {
	    return user;
	}
	
	/**
     * POST  /users  : Creates a new user.
     * <p>
     * Creates a new user if email is not already used.
     * </p>
     *
     * @param user the user to create
     * @param request the HTTP request
     * @return the ResponseEntity with status 201 (Created) 
     *         or with status 409 (Conflict) if email is already in use
     *         or with status 400 (Bad Request) if user is not valid
     * @throws URISyntaxException when the Character Encoding is not supported in URI
     */
	@PostMapping(value="/create")
	public ResponseEntity create(@RequestBody @Valid User user,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
		
		try{
			//Check if email has registered before if not exception is thrown
			userDetailsService.loadUserByUsername(user.getUsername());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		} catch(UsernameNotFoundException e) {
			userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		}
	}
}
