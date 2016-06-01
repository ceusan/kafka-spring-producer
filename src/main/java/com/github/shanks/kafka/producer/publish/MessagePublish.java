package com.github.shanks.kafka.producer.publish;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.shanks.kafka.producer.PublishMsg;

@Service
public class MessagePublish extends PublishMsg {

	@Value("${helloworld.topic}")
	private String topic;
	
	@Override
	public String publishTopic() {
		return topic;
	}

}
