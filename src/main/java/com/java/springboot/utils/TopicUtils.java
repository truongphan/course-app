package com.java.springboot.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.java.springboot.course.Course;
import com.java.springboot.topic.Topic;
import com.java.springboot.topic.TopicEntity;

public class TopicUtils {

	public static Topic toTopicModel(TopicEntity entity) {
		List<Course> courses = entity.getCourses().stream().map(c -> CourseUtils.toCourseModel(c)).collect(Collectors.toList());
		return Topic.builder().id(entity.getId()).name(entity.getName()).description(entity.getDescription()).courses(courses).build();
	}
	
	public static TopicEntity toTopicEntity(Topic model) {
		return TopicEntity.builder().id(model.getId()).name(model.getName()).description(model.getDescription()).build();
	}
}
