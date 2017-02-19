package com.orcunguducu.dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.orcunguducu.dynamic.configuration.UserDetailsAdapter;
import com.orcunguducu.dynamic.domain.User;
import com.orcunguducu.dynamic.repository.AddressRepository;
import com.orcunguducu.dynamic.repository.UserRepository;

/**
 * Service class for managing users.
 */
@Service
public class UserServiceImpl implements UserDetailsService,UserService{
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		User myUser = userRepository.getByUsername(username);
		
		if (myUser == null) {
	    	throw new UsernameNotFoundException(username);
	    }
		return new UserDetailsAdapter(myUser);
	}

	@Override
	public void saveUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		User savedUser = userRepository.save(user);
		
		savedUser.getAddress().stream().forEach(a->a.setUserId(savedUser.getId()));
		addressRepository.save(savedUser.getAddress());
	}
	
	@Override
	public User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Anonymous user
		if(auth.getPrincipal() instanceof String)
			return null;
		UserDetailsAdapter userAdapter = (UserDetailsAdapter) auth.getPrincipal();
		return userAdapter.getUser();
	}
}
