package com.java.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.java.springboot.user.User;
import com.java.springboot.user.UserService;

@SpringBootApplication
public class CourseAppApplication implements CommandLineRunner{
	
	@Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(CourseAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User("tpv",passwordEncoder.encode("123456"));
        System.out.println(userService.saveUser(user));
	}

}
