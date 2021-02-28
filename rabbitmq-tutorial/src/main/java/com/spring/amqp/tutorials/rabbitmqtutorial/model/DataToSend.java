package com.spring.amqp.tutorials.rabbitmqtutorial.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class DataToSend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static AtomicLong idGenerator = new AtomicLong(0);

	Long id;
	String data;

	public DataToSend() {
	}

	public DataToSend(String data) {
		this.id = idGenerator.getAndIncrement();
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataToSend [id=" + id + ", data=" + data + "]";
	}

}
