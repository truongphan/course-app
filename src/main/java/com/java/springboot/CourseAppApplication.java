package com.java.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseAppApplication {
	
	/*
	 * @Autowired private UserRepository userRepository;
	 * 
	 * @Autowired private BCryptPasswordEncoder encoder;
	 */

	public static void main(String[] args) {
		SpringApplication.run(CourseAppApplication.class, args);
	}

	/*
	 * @Override public void run(String... args) throws Exception { User user = new
	 * User(); user.setUsername("truongphan")
	 * ;
	 * user.setPassword(encoder.encode("123456")); userRepository.save(user); }
	 */

}
