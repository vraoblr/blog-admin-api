package com.blog.admin.exception;

/**
 * Custom exception class for user details-related errors.
 * 
 * @author vinay.vyasarao
 */
public class UserDetailsException extends Exception {

	/**
     * Constructs a new user details exception with the specified detail message.
     *
     * @param message the detail message
     */
	public UserDetailsException(String message) {
        super(message);
    }

	/**
     * Constructs a new user details exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public UserDetailsException(String message, Throwable cause) {
        super(message, cause);
    }
}
