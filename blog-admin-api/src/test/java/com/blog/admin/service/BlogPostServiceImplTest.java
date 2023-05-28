package com.blog.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.blog.admin.exception.ApiRequestException;
import com.blog.admin.exception.BlogPostException;
import com.blog.admin.helper.BlogRequestHelper;
import com.blog.admin.model.BlogPost;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

/**
 * Unit test for BlogPostServiceImpl class
 * 
 * @author vinay.vyasarao
 */
class BlogPostServiceImplTest {

	@Mock
	private ObjectMapper mapper;

	@Mock
	private BlogRequestHelper requestHelper;

	private static String USER_POST_URL = "https://jsonplaceholder.typicode.com/posts";
	
	@InjectMocks
	private BlogPostServiceImpl blogPostService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		blogPostService = new BlogPostServiceImpl(new ObjectMapper(), requestHelper);
	}
	
	@Test
	@SneakyThrows
	void testGetAllPosts() {

		String responseBody = "[{\"userId\": 1, \"id\": 1, \"title\": \"Title 1\", \"body\":\"User Post 1\"}]";

		BlogPost[] expectedPosts = new BlogPost[1];
		
		expectedPosts[0] = BlogPost.builder()
						.id(1)
						.userId(1)
						.title("Title 1")
						.body("User Post 1")
						.build();
						
		when(requestHelper.sendRestRequest(USER_POST_URL)).thenReturn(responseBody);

		//BlogPostServiceImpl blogPostService = new BlogPostServiceImpl(new ObjectMapper(), requestHelper);

		Field userPostURLField = BlogPostServiceImpl.class.getDeclaredField("userPostURL");
		userPostURLField.setAccessible(true);
		userPostURLField.set(blogPostService, USER_POST_URL);

		List<BlogPost> actualPosts = blogPostService.getAllPosts();

		assertEquals(Arrays.asList(expectedPosts), actualPosts);
	}

	@Test
	@SneakyThrows
	void testGetAllPostsApiRequestException() {
		BlogPostService blogPostService = new BlogPostServiceImpl(mapper, requestHelper);

		Field userPostURLField = BlogPostServiceImpl.class.getDeclaredField("userPostURL");
		userPostURLField.setAccessible(true);
		userPostURLField.set(blogPostService, USER_POST_URL);

		when(requestHelper.sendRestRequest(USER_POST_URL))
				.thenThrow(new ApiRequestException("Internal occurred during REST request"));

		assertThrows(BlogPostException.class, () -> {
			blogPostService.getAllPosts();
		});
	}

}
