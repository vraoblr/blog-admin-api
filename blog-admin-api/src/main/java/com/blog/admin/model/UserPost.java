package com.blog.admin.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user and their associated blog posts.
 * 
 * @author vinay.vyasarao 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6276213497415888070L;

	private User user;
	
	private List<BlogPost> blogPost;
}
