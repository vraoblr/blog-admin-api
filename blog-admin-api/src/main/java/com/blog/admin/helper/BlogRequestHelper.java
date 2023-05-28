package com.blog.admin.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.blog.admin.exception.ApiRequestException;

import lombok.extern.slf4j.Slf4j;

/**
 * Helper class for making REST requests using WebClient.
 * 
 * @author vinay.vyasarao
 */
@Slf4j
@Component
public class BlogRequestHelper {

	private WebClient webClient;
	
	/**
     * Constructs a new BlogRequestHelper with the specified WebClient instance.
     *
     * @param webClient the WebClient instance to use for REST requests
     */
	@Autowired
	public BlogRequestHelper(WebClient webClient) {
		this.webClient = webClient;
	}

	/**
     * Sends a REST request to the specified URL and retrieves the response.
     *
     * @param url the URL to send the request to
     * @return the response body as a String
     * @throws ApiRequestException if an error occurs during the REST request
     */
	public String sendRestRequest(String url) throws ApiRequestException {
		log.info("Fetching data from URL {}", url);
		try {
			return webClient
					.get()
					.uri(url)
					.retrieve()
					.bodyToMono(String.class)
					.block();

		} catch (Exception e) {
			log.error("Error occurred during REST request: {}", e.getMessage());
			throw new ApiRequestException("Error occurred during REST request", e);
		}
	}

}