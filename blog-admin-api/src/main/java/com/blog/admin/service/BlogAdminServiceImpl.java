package com.blog.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.admin.exception.BlogPostException;
import com.blog.admin.exception.UserDetailsException;
import com.blog.admin.model.BlogPost;
import com.blog.admin.model.User;
import com.blog.admin.model.UserPost;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementation of the BlogAdminService interface. Provides methods to
 * retrieve all blog posts and user details.
 * 
 * @author vinay.vyasarao
 */
@Service
@Slf4j
public class BlogAdminServiceImpl implements BlogAdminService {

	@Autowired
	private UserService userService;

	@Autowired
	private BlogPostService blogPostService;

	@Override
	public Flux<UserPost> retrieveAllBlogPosts() 
			throws BlogPostException, UserDetailsException {

		log.info("Fetching all user details");

		List<User> userList = userService.getAllUsers();

		List<BlogPost> blogPostList = blogPostService.getAllPosts();

		return combineLists(userList, blogPostList);
	}

	/**
	 * Combines the lists of users and blog posts into UserPost objects.
	 *
	 * @param userList     the list of users
	 * @param blogPostList the list of blog posts
	 * @return a Flux of UserPost objects representing the combined user and blog
	 *         post data
	 */
	private Flux<UserPost> combineLists(List<User> userList, List<BlogPost> blogPostList) {
		return Flux.fromIterable(userList)
				.flatMap(user -> {
							List<BlogPost> userBlogPosts = blogPostList.stream()
									.filter(blogPost -> blogPost.getUserId() == user.getId())
									.collect(Collectors.toList());
						return Mono.just(new UserPost(user, userBlogPosts));
				});
	}
}
