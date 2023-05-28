package com.blog.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.blog.admin.exception.ApiRequestException;
import com.blog.admin.exception.UserDetailsException;
import com.blog.admin.helper.BlogRequestHelper;
import com.blog.admin.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the UserService interface that provides methods for managing users.
 * 
 * @author vinay.vyasarao
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private ObjectMapper mapper;

	private BlogRequestHelper requestHelper;

	@Value("${blog.userdetails.url}")
	private String userDetailsURL;

	/**
     * Constructs a new UserServiceImpl with the specified dependencies.
     *
     * @param mapper         the ObjectMapper used for JSON processing
     * @param requestHelper  the BlogRequestHelper used for making API requests
     */
	@Autowired
	public UserServiceImpl(ObjectMapper mapper, BlogRequestHelper requestHelper) {
		this.mapper = mapper;
		this.requestHelper = requestHelper;
	}
	
	@Override
	public List<User> getAllUsers() throws UserDetailsException {
	    try {
	        log.info("Fetching all user details");

	        String userDetailsResponse = requestHelper.sendRestRequest(userDetailsURL);

	        List<User> userList = mapper.readValue(userDetailsResponse, new TypeReference<List<User>>() {});

	        log.info("Retrieved {} users", userList.size());

	        return userList;
	    } catch (ApiRequestException e) {
	        log.error("Api Request failed for getAllUsers: {}", e.getMessage());
	        throw new UserDetailsException(e.getMessage(), e);
	    } catch (JsonProcessingException e) {
	        log.error("JsonProcessing failed for getAllUsers: {}", e.getMessage());
	        throw new UserDetailsException("Error occurred during JSON processing", e);
	    }
	}
}
