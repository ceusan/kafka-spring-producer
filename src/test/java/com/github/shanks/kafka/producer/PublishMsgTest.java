package com.github.shanks.kafka.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.shanks.kafka.msg.MessageModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-kafka.xml" })
public class PublishMsgTest {

	@Autowired
	private PublishMsg publishMsg;
	
	@Test
	public void send() {
		MessageModel message = null;
		for (int i = 0; i< 10 ;i++) {
			 message = new MessageModel();
			 message.setMessageId(Long.valueOf(i));
				message.setMessage( String.format("message id = %d", i));
				publishMsg.send(message);
		}
	}
	
}
