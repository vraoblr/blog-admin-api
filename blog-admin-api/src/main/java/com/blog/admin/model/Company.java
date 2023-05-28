package com.blog.admin.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the User's company details
 * 
 * @author vinay.vyasarao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8432935703093136805L;
	
	private String name;
	
    private String catchPhrase;
    
    private String bs;
}
