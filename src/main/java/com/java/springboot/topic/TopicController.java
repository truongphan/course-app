package com.java.springboot.topic;

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

import com.java.springboot.utils.TopicUtils;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	@GetMapping("/topics")
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics().stream().map(t -> TopicUtils.toTopicModel(t)).collect(Collectors.toList());
	}
	
	@GetMapping("/topics/{topic-id}")
	public Topic getTopicById(@PathVariable("topic-id") Long id) {
		return TopicUtils.toTopicModel(topicService.getTopicById(id));
	}
	
	@PostMapping("/topics")
	public void addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
	}
	
	@PutMapping("/topics/{topic-id}")
	public void addTopic(@RequestBody Topic topic, @PathVariable("topic-id") Long id) throws NotFoundException {
		topicService.updateTopic(topic, id);
	}
	
	@DeleteMapping("/topics/{topic-id}")
	public void deleteTopic(@PathVariable("topic-id") Long id) {
		topicService.deleteTopic(id);
	}
}
