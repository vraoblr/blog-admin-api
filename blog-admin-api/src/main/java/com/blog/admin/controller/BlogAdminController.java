package com.blog.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.blog.admin.model.UserPost;
import com.blog.admin.service.BlogAdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Controller class for handling blog administration requests.
 * 
 * @author vinay.vyasarao
 */
@RestController
@Slf4j
@Tag(name = "Admin Controller")
public class BlogAdminController {
	
	@Autowired
	private BlogAdminService adminService;

	/**
	 * Fetches the complete list of blog users along with their respective blog posts.
	 * 
	 * @return Flux of UserPost objects containing user and blog post data
	 */
	@Operation(summary = "Fetch the complete list of Blog Users along with their respective blog posts")
	@GetMapping(value="/userPosts", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<UserPost> userPosts(){
		
		try {
            log.info("Fetching all users & their posts");

            Flux<UserPost> userPosts = adminService.retrieveAllBlogPosts();

            return userPosts;
            
        } catch (Exception e) {

            log.error("Error occurred while fetching user posts: {}", e.getMessage());

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
	}

}
