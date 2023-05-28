package com.blog.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.blog.admin.model.BlogPost;
import com.blog.admin.model.User;
import com.blog.admin.model.UserPost;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;

/**
 * Unit tests for the BlogAdminServiceImpl class.
 * 
 * @author vinay.vyasarao
 */
class BlogAdminServiceImplTest {

	@Mock
	private UserService userService;

	@Mock
	private ObjectMapper mapper;

	@Mock
	private BlogPostService blogPostService;

	@InjectMocks
	private BlogAdminServiceImpl blogAdminService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test case for the retrieveAllBlogPosts() method.
	 * Verifies that the method retrieves the expected user and blog post data and returns a Flux of UserPost objects.
	 */
	@Test
    public void testRetrieveAllBlogPosts() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "User 1", "user1", "user1email@gmail.com", null, null, null, null));
        userList.add(new User(2, "User 2", "user2", "user2email@gmail.com", null, null, null, null));

        List<BlogPost> blogPostList = new ArrayList<>();
        blogPostList.add(new BlogPost(1, 1, "Title 1", "User Post 1"));
        blogPostList.add(new BlogPost(1, 2, "Title 2", "User Post 2"));
        blogPostList.add(new BlogPost(2, 3, "Title 3", "User Post 3"));

        when(userService.getAllUsers()).thenReturn(userList);
        when(blogPostService.getAllPosts()).thenReturn(blogPostList);

        Flux<UserPost> userPosts = blogAdminService.retrieveAllBlogPosts();

        List<UserPost> userPostList = userPosts.collectList().block();
        
        assertEquals(2, userPostList.size());
        assertEquals(2, userPostList.get(0).getBlogPost().size());
        assertEquals(1, userPostList.get(1).getBlogPost().size());
    }

}
