package com.java.springboot.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    
    public User(String username, String pass) {
    	this.username = username;
    	this.password = pass;
	}
}
