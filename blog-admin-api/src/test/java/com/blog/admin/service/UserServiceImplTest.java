package com.blog.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.blog.admin.exception.ApiRequestException;
import com.blog.admin.exception.UserDetailsException;
import com.blog.admin.helper.BlogRequestHelper;
import com.blog.admin.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

/**
 * Unit test for UserServiceImpl class
 * 
 * @author vinay.vyasarao
 */
class UserServiceImplTest {

	@Mock
	private ObjectMapper mapper;

	@Mock
	private BlogRequestHelper requestHelper;

	@InjectMocks
	private UserServiceImpl userService;

	private static String USER_DETAILS_URL = "https://jsonplaceholder.typicode.com/users";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		userService = new UserServiceImpl(new ObjectMapper(), requestHelper);
	}

	@Test
	@SneakyThrows
	void testGetAllPosts() {

		String userDetailsResponse = "[{\"id\": 1, \"name\": \"user1\"}, {\"id\": 2, \"name\": \"user2\"}]";

		List<User> expectedUserList = new ArrayList<User>();
		expectedUserList.add(User.builder().id(1).name("user1").build());
		expectedUserList.add(User.builder().id(2).name("user2").build());

		when(requestHelper.sendRestRequest(null)).thenReturn(userDetailsResponse);

		List<User> userList = userService.getAllUsers();

		// Assert
		assertEquals(expectedUserList.size(), userList.size());
		assertEquals(expectedUserList, userList);

	}

	@Test
	@SneakyThrows
	void testUserDetailsException() {
		// Arrange
		String errorMessage = "API request failed";
		when(requestHelper.sendRestRequest(null))
				.thenThrow(new ApiRequestException(errorMessage));

		// Act & Assert
		assertThrows(UserDetailsException.class, () -> userService.getAllUsers());
	}

}
