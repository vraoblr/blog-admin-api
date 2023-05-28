package com.blog.admin.service;

import java.util.List;

import com.blog.admin.exception.UserDetailsException;
import com.blog.admin.model.User;

/**
 * Service interface for managing users.
 * 
 * @author vinay.vyasarao
 */
public interface UserService {

	/**
     * Retrieves a list of all users.
     *
     * @return the list of users
     * @throws UserDetailsException if an error occurs while retrieving the user details
     */
	public List<User> getAllUsers() throws UserDetailsException;
	
}
