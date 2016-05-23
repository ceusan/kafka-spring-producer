package com.github.shanks.kafka.producer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.shanks.kafka.msg.MessageModel;
import com.github.shanks.kafka.msg.json.JsonUtils;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PublishMsg {

	@Value("${helloworld.topic}")
	private String topic;

	@Value("${helloworld.brokerList}")
	private String brokerList;

	@Setter
	private Producer<String, String> producer;

	@PostConstruct
	public void init() {
		Properties props = new Properties();
		props.put("bootstrap.servers", brokerList);
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("batch.size", 116384);
		producer = new KafkaProducer<>(props);
	}

	public void send(MessageModel message) {
		String msg = JsonUtils.stringify(message);
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, msg);
		Future<RecordMetadata> result = producer.send(record);
		try {
			log.info("send");
			log.info("offset : {}, partition {}, topic {}", result.get().offset(), result.get().partition(),
					result.get().topic());
		} catch (InterruptedException | ExecutionException e) {
			log.error(e.getMessage(), e);
		}
	}

}
