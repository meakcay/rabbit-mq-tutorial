package com.spring.amqp.tutorials.rabbitmqtutorial.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${tutorial.rabbitmq.queue}")
	String queueName;

	@Value("${tutorial.rabbitmq.exchange}")
	String exchange;

	@Value("${tutorial.rabbitmq.routingkey}")
	private String routingkey;
	
	@Value("${tutorial.rabbitmq.topic_queue}")
	String topicQueueName;
	
	@Value("${tutorial.rabbitmq.topic_exchange}")
	private String topicExchange;
	
	@Value("${tutorial.rabbitmq.topic}")
	private String topic;

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}
	
	@Bean
	Queue topicQueue() {
		return new Queue(topicQueueName, false);
	}
	
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(topicExchange);
	}

	@Bean
	Binding topicBinding(Queue topicQueue, TopicExchange exchange) {
		return BindingBuilder.bind(topicQueue).to(exchange).with(topic);
	}
	
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
	    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	    rabbitTemplate.setMessageConverter(jsonMessageConverter());
	    return rabbitTemplate;
	}
}
