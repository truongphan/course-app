package com.java.springboot.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.springboot.utils.TopicUtils;

import javassist.NotFoundException;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	public List<TopicEntity> getAllTopics() {
		List<TopicEntity> topics = new ArrayList<TopicEntity>();
		topicRepository.findAll().forEach(topics::add);
		return topics;
	}
	
	public TopicEntity getTopicById(Long id) {
		return topicRepository.findById(id).get();
	}

	public void addTopic(Topic topic) {
		TopicEntity entity = TopicUtils.toTopicEntity(topic);
		topicRepository.save(entity);
	}

	public void deleteTopic(Long id) {
		topicRepository.deleteById(id);;;
	}

	public void updateTopic(Topic topic, Long id) throws NotFoundException {
		if (getTopicById(id) == null) {
			throw new NotFoundException("Topic Not Found.");
		}
		topicRepository.save(TopicUtils.toTopicEntity(topic));
	}
}
