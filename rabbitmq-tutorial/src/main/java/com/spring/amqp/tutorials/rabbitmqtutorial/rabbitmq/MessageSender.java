package com.spring.amqp.tutorials.rabbitmqtutorial.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);
	
	@Autowired
	private AmqpTemplate template;
	
	@Value("${tutorial.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${tutorial.rabbitmq.topic_exchange}")
	private String topicExchange;
	
	@Value("${tutorial.rabbitmq.routingkey}")
	private String routingkey;	
	
	@Value("${tutorial.rabbitmq.topic}")
	private String topic;	
	
	public void send(Object data) {
		
		template.convertAndSend(exchange, routingkey, data);
		
		logger.info("Message sent via amqp: {}", data);
	}
	
	public void send(Object data, String topicDetail) {
		
		String specificTopic = topic.replace("*", topicDetail);
		template.convertAndSend(topicExchange, specificTopic, data);
		
		logger.info("Message [{}] sent via amqp with topic: {}", data, specificTopic);
	}

}
