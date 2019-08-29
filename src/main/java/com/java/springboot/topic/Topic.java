package com.java.springboot.topic;

import java.util.List;

import com.java.springboot.course.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Topic {
	
	private Long id;
	private String name;
	private String description;
	private List<Course> courses;
}
