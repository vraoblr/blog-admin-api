package com.blog.admin.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the Blog Posts
 * 
 * @author vinay.vyasarao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3971122283818111354L;
	
    private int userId;
    private int id;
    private String title;
    private String body;

}
