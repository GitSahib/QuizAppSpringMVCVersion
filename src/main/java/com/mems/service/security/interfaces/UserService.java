package com.mems.service.security.interfaces;

import java.util.List;

import com.mems.domain.User;

public interface UserService {
    void save(User user);
    
    User findByUsername(String username);

	void deleteUser(String username);

	
	void changePassword(String username, String newPassword);

	void updateUser(String username, User user);

	boolean userExists(String username);

	boolean authenticate(String password);

	User getLoggedInUser();

	List<User> findAll();

	User findById(Long id);
}
