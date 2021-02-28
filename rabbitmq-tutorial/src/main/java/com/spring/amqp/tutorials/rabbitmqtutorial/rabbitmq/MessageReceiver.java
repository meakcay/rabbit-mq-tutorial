package com.spring.amqp.tutorials.rabbitmqtutorial.rabbitmq;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.spring.amqp.tutorials.rabbitmqtutorial.model.DataToSend;

@Service
public class MessageReceiver {

	
	private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	
	private List<DataToSend> messagesReceived = new ArrayList<DataToSend>();
	private List<DataToSend> messagesReceivedOnTopic = new ArrayList<DataToSend>();

	@RabbitListener(queues = "${tutorial.rabbitmq.queue}")
	public void receiveMessage(DataToSend message) {
		
		logger.info("Message received: {}", message);
		
		messagesReceived.add(message);
	}
	
	@RabbitListener(queues = "${tutorial.rabbitmq.topic_queue}")
	public void receiveTopicMessages(DataToSend message) {
		
		logger.info("Message received on topic: {}", message);
		
		messagesReceivedOnTopic.add(message);
	}

	public List<DataToSend> getMessagesReceived() {
		return messagesReceived;
	}

	public List<DataToSend> getMessagesReceivedOnTopic() {
		return messagesReceivedOnTopic;
	}
}
