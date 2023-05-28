package com.blog.admin.exception;

/**
 * Custom exception class for API request-related errors.
 * 
 * @author vinay.vyasarao
 */
public class ApiRequestException extends Exception{

	/**
     * Constructs a new API request exception with the specified detail message.
     *
     * @param message the detail message
     */
	public ApiRequestException(String message) {
        super(message);
    }

	/**
     * Constructs a new API request exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
