package com.github.shanks.kafka.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.shanks.kafka.msg.json.JsonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class PublishMsg {

	@Autowired
	private KafkaPublish<String,String> kafkaPublish;
	
	public void send(Object message) {
		String msg = JsonUtils.stringify(message);
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(publishTopic(), msg);
		Future<RecordMetadata> result = kafkaPublish.send(record);
		try {
			log.info("send");
			log.info("offset : {}, partition {}, topic {}", result.get().offset(), result.get().partition(),
					result.get().topic());
		} catch (InterruptedException | ExecutionException e) {
			log.error(e.getMessage(), e);
		}
	}

	public abstract String publishTopic();
	
}
