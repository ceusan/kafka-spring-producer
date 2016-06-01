package com.github.shanks.kafka.producer.publish;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.shanks.kafka.producer.PublishMsg;

@Service
public class ArticlePublish extends PublishMsg {

	@Value("${article.topic}")
	private String topic;
	
	@Override
	public String publishTopic() {
		return topic;
	}

}
