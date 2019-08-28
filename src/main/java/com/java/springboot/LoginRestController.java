package com.java.springboot;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.springboot.jwt.JwtTokenProvider;
import com.java.springboot.payload.LoginRequest;
import com.java.springboot.payload.LoginResponse;
import com.java.springboot.payload.RandomStuff;
import com.java.springboot.user.CustomUserDetails;
import com.java.springboot.user.User;
import com.java.springboot.user.UserService;

@RestController
@RequestMapping("/api")
public class LoginRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private UserService userService ;
    
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
    	return userService.saveUser(user);
    }

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    	// Authenticate from user,pass
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        // if no exception, set Authorization info into Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // return jwt for user
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @GetMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("If jwt is valid. This message will be showed.");
    }

}
