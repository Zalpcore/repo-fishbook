package com.fishbook.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fishbook.application.model.User;

@Service
public interface UserService {

	List<User> getAllUsers();
	void saveUser(User user);
	User getUserById(Long id);
	void deleteUserById(Long id);
	
}
