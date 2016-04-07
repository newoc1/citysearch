package grok.citysearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import grok.citysearch.user.User;
import grok.citysearch.user.UserService;

@RestController
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/users/me")
	public User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.get(auth.getName());
	}
	
	@RequestMapping("/users/{username}")
	public User getUser(@PathVariable("username") String username) {
		return userService.get(username);
	}
}
