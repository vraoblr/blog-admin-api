package com.blog.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for the Blog Admin application.
 * This class provides bean definitions for the application's configuration.
 * 
 * @author vinay.vyasarao
 */
@Configuration
public class BlogAdminConfig {

	/**
	 * Creates and configures a WebClient bean.
	 * @return the WebClient instance
	 */
	@Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
