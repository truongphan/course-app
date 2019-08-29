package com.java.springboot.course;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.springboot.utils.CourseUtils;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return courseService.getAllCourses().stream().map(c -> CourseUtils.toCourseModel(c)).collect(Collectors.toList());
	}
	
	@GetMapping("/courses/{course-id}")
	public Course getCourseById(@PathVariable("course-id") Long id) {
		return CourseUtils.toCourseModel(courseService.getCourseById(id));
	}
	
	@PostMapping("/topics/{topic-id}/courses")
	public void addCourse(@PathVariable("topic-id") Long topicId, @RequestBody Course course) throws NotFoundException {
		courseService.addCourse(topicId, course);
	}
	
	@PutMapping("/topics/{topic-id}/courses/{course-id}")
	public void updateCourse(@PathVariable("topic-id") Long topicId, @RequestBody Course course, @PathVariable("course-id") Long courseId) throws NotFoundException {
		courseService.updateCourse(topicId, course, courseId);
	}
	
	@DeleteMapping("/courses/{course-id}")
	public void deleteCourse(@PathVariable("course-id") Long id) {
		courseService.deleteCourse(id);
	}
}
