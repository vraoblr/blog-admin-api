package com.blog.admin.service;

import java.util.List;

import com.blog.admin.exception.BlogPostException;
import com.blog.admin.model.BlogPost;

/**
 * Service interface for managing blog posts.
 * 
 * @author vinay.vyasarao
 */
public interface BlogPostService {

	/**
	 * Retrieves all blog posts.
	 *
	 * @return a list of BlogPost objects representing all the blog posts
	 * @throws BlogPostException if an error occurs while retrieving the blog posts
	 */
	public List<BlogPost> getAllPosts() throws BlogPostException;
	
}
