package com.spring.integration.demo1.invoker;

import org.springframework.messaging.Message;

public class SuscriberInvoker {

	public void handleMessage(Message<?> message){
		System.out.println("message: " + (message == null ? message : message.getPayload()));
	}
}
