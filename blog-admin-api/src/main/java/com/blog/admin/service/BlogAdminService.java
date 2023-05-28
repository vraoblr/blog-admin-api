package com.blog.admin.service;

import com.blog.admin.exception.BlogPostException;
import com.blog.admin.exception.UserDetailsException;
import com.blog.admin.model.UserPost;

import reactor.core.publisher.Flux;

/**
 * Service interface for blog administration. Provides methods to retrieve all
 * blog posts and user details.
 * 
 * @author vinay.vyasarao
 */
public interface BlogAdminService {

	/**
	 * Retrieves all blog posts along with user details.
	 *
	 * @return a Flux of UserPost objects representing the combined user and blog
	 *         post data
	 * @throws BlogPostException    if an error occurs while retrieving the blog
	 *                              posts
	 * @throws UserDetailsException if an error occurs while retrieving the user
	 *                              details
	 */
	public Flux<UserPost> retrieveAllBlogPosts() throws BlogPostException, UserDetailsException;

}