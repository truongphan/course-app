package com.java.springboot.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.springboot.topic.TopicEntity;
import com.java.springboot.topic.TopicService;
import com.java.springboot.utils.CourseUtils;

import javassist.NotFoundException;

@Service
public class CourseService {
	
	@Autowired
	private TopicService topicService;

	@Autowired
	private CourseRepository courseRepository;
	
	public List<CourseEntity> getAllCourses() {
		List<CourseEntity> courses = new ArrayList<CourseEntity>();
		courseRepository.findAll().forEach(courses::add);
		return courses;
	}
	
	public CourseEntity getCourseById(Long id) {
		return courseRepository.findById(id).get();
	}

	public void addCourse(Long topicId, Course course) throws NotFoundException {
		TopicEntity topicEntity = topicService.getTopicById(topicId);
		if (topicEntity == null) {
			throw new NotFoundException("Topic not found.");
		}
		CourseEntity entity = CourseUtils.toCourseEntity(course);
		entity.setTopic(topicEntity);
		courseRepository.save(entity);
	}

	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}

	public void updateCourse(Long topicId, Course course, Long id) throws NotFoundException {
		TopicEntity topicEntity = topicService.getTopicById(topicId);
		if (topicEntity == null) {
			throw new NotFoundException("Topic not found.");
		}
		CourseEntity courseEntity = getCourseById(id);
		if (courseEntity == null) {
			throw new NotFoundException("Course Not Found.");
		}
		courseEntity = CourseUtils.toCourseEntity(course);
		courseEntity.setTopic(topicEntity);
		courseRepository.save(courseEntity);
	}
}
