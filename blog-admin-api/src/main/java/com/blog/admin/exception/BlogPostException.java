package com.blog.admin.exception;

/**
 * Custom exception class for blog post-related errors.
 * 
 * @author vinay.vyasarao
 */
public class BlogPostException extends Exception {

	/**
     * Constructs a new blog post exception with the specified detail message.
     *
     * @param message the detail message
     */
	public BlogPostException(String message) {
        super(message);
    }

	/**
     * Constructs a new blog post exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public BlogPostException(String message, Throwable cause) {
        super(message, cause);
    }
}
