package com.java.springboot.utils;

import com.java.springboot.course.Course;
import com.java.springboot.course.CourseEntity;

public class CourseUtils {

	public static Course toCourseModel(CourseEntity entity) {
		return Course.builder().id(entity.getId()).name(entity.getName()).description(entity.getDescription()).build();
	}
	
	public static CourseEntity toCourseEntity(Course model) {
		return CourseEntity.builder().id(model.getId()).name(model.getName()).description(model.getDescription()).build();
	}
}
