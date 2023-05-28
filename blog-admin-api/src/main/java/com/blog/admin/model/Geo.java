/**
 * 
 */
package com.blog.admin.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the User's Geo Location details
 * 
 * @author vinay.vyasarao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Geo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8290319056417385119L;
	
	private String lat;
	
    private String lng;

}
