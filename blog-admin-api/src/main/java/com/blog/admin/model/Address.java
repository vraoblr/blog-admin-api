package com.blog.admin.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an user address.
 * 
 * @author vinay.vyasarao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6689324940123229390L;
	
	private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
}
