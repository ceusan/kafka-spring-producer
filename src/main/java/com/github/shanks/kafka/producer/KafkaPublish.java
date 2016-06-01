package com.github.shanks.kafka.producer;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaPublish<K,V> {

	private Producer<K,V> producer;
	
	public KafkaPublish(Properties properties) {
		producer = new KafkaProducer<>(properties);
	}

	public Future<RecordMetadata> send(ProducerRecord<K,V> record) {
		return producer.send(record);
	}
	
	
}
