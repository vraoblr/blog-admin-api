package com.blog.admin.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import com.blog.admin.model.BlogPost;
import com.blog.admin.model.User;
import com.blog.admin.model.UserPost;
import com.blog.admin.service.BlogAdminService;

import lombok.SneakyThrows;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Unit tests for the BlogAdminController class.
 * 
 * @author vinay.vyasarao
 */
class BlogAdminControllerTest {

    MockMvc mockMvc;
    
    @Mock
    BlogAdminService adminService;
    
    @InjectMocks
    BlogAdminController blogAdminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        mockMvc = MockMvcBuilders.standaloneSetup(blogAdminController).build();
    }

    /**
     * Test case for the userPosts() method.
     * Verifies that the method returns a Flux of UserPost objects as expected.
     */
    @Test
    void testUserPostData() throws Exception {
    	
        User user1 = User.builder().id(1).name("UserName1").build();
        User user2 = User.builder().id(2).name("UserName2").build();

        List<BlogPost> posts1 = new ArrayList<>();
        posts1.add(new BlogPost(1, 1, "First Post", "Content1"));
        posts1.add(new BlogPost(1, 2, "Second Post", "Content2"));

        List<BlogPost> posts2 = new ArrayList<>();
        posts2.add(new BlogPost(2, 3, "Third Post", "Content3"));
        posts2.add(new BlogPost(2, 4, "Fourth Post", "Content4"));

        UserPost userPost1 = new UserPost(user1, posts1);
        UserPost userPost2 = new UserPost(user2, posts2);

        when(adminService.retrieveAllBlogPosts()).thenReturn(Flux.just(userPost1, userPost2));

        Flux<UserPost> result = blogAdminController.userPosts();
        
        StepVerifier.create(result)
			        .expectNext(userPost1, userPost2)
			        .expectComplete()
			        .verify();
    }

    /**
     * Test case for the userPosts() method when an exception occurs.
     * Verifies that the method throws a ResponseStatusException with the appropriate status and message.
     */
    @Test
    @SneakyThrows
    public void testUserPostsInternalServerError() {
        when(adminService.retrieveAllBlogPosts()).thenThrow(new RuntimeException("Something went wrong"));

        ResponseStatusException exception = org.junit.jupiter.api.Assertions.assertThrows(
                ResponseStatusException.class,
                () -> blogAdminController.userPosts()
        );

        org.junit.jupiter.api.Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        org.junit.jupiter.api.Assertions.assertEquals("Internal Server Error", exception.getReason());

    }
}

