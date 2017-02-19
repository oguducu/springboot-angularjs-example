package com.orcunguducu.dynamic.service;

import com.orcunguducu.dynamic.domain.User;

public interface UserService {
	public void saveUser(User user);
	public User getLoggedInUser();
}
