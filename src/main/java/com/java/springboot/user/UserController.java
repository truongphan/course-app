package com.java.springboot.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.java.springboot.filter.JwtTokenProvider;
import com.java.springboot.login.LoginRequest;
import com.java.springboot.login.LoginResponse;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping("/login")
	public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		// Authenticate from user,pass
		Authentication authentication = null;
		try {
			authentication = authenticationProvider.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		// if no exception, set Authorization info into Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// return jwt for user
		String jwt = tokenProvider.generateToken((CustomUserDetails)authentication.getPrincipal());
		return new LoginResponse(jwt);
	}
}
