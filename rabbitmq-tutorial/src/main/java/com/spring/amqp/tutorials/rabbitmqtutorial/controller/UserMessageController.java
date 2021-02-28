package com.spring.amqp.tutorials.rabbitmqtutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.amqp.tutorials.rabbitmqtutorial.model.DataToSend;
import com.spring.amqp.tutorials.rabbitmqtutorial.rabbitmq.MessageReceiver;
import com.spring.amqp.tutorials.rabbitmqtutorial.rabbitmq.MessageSender;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class UserMessageController {

	@Autowired
	MessageSender sender;
	
	@Autowired
	MessageReceiver receiver;

	@GetMapping("/send")
	public String publish(@RequestParam(name = "data", required = true) String data) {

		DataToSend dataToSend = new DataToSend(data);
		sender.send(dataToSend);
		
		return dataToSend.toString();
	}
	
	@GetMapping("/sendTopic")
	public String publishTopic(
			@RequestParam(name = "data", required = true) String data
			, @RequestParam(name = "topic", required = true) String topic) {

		DataToSend dataToSend = new DataToSend(data);
		sender.send(dataToSend, topic);
		
		return dataToSend.toString();
	}
	
	@GetMapping("/showReceived")
	public List<DataToSend> showReceived() {
		var messagesReceived = receiver.getMessagesReceived();
		
		return messagesReceived;
		
	}
	
	@GetMapping("/showReceivedTopic")
	public List<DataToSend> showReceivedTopic() {
		var messagesReceived = receiver.getMessagesReceivedOnTopic();
		
		return messagesReceived;
		
	}

}
