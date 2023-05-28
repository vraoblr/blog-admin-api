package com.blog.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.blog.admin.exception.ApiRequestException;
import com.blog.admin.exception.BlogPostException;
import com.blog.admin.helper.BlogRequestHelper;
import com.blog.admin.model.BlogPost;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the BlogPostService interface.
 * 
 * @author vinay.vyasarao
 */
@Slf4j
@Service
public class BlogPostServiceImpl implements BlogPostService {

	private ObjectMapper mapper;

	private BlogRequestHelper requestHelper;

	@Value("${blog.userpost.url}")
	private String userPostURL;

	@Autowired
	public BlogPostServiceImpl(ObjectMapper mapper, BlogRequestHelper requestHelper) {
		this.mapper = mapper;
		this.requestHelper = requestHelper;
	}

	@Override
	public List<BlogPost> getAllPosts() throws BlogPostException {
	    try {
	        log.info("Fetching all posts details");

	        String blogPostResponse = requestHelper.sendRestRequest(userPostURL);

	        List<BlogPost> blogPostList = mapper.readValue(blogPostResponse, new TypeReference<List<BlogPost>>() {});

	        log.info("Retrieved {} blog posts", blogPostList.size());

	        return blogPostList;
	    } catch (ApiRequestException e) {
	        log.error("Api Request failed for getPostForUser: {}", e.getMessage());
	        throw new BlogPostException(e.getMessage(), e);
	    } catch (JsonProcessingException e) {
	        log.error("JsonProcessing failed for getPostForUser: {}", e.getMessage());
	        throw new BlogPostException("Error occurred during JSON processing", e);
	    }
	    
	}
}
