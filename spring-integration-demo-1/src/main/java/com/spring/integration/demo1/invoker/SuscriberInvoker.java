package com.spring.integration.demo1.invoker;

import org.springframework.messaging.Message;

public class SuscriberInvoker {
	
	private int id;
	
	public SuscriberInvoker(){
		this.id = 0;
	}
	
	public SuscriberInvoker(int id) {
		this.id = id;
	}

	public void handleMessage(Message<?> message){
		System.out.println("suscriber["+id+"] "+"message: " + (message == null ? message : message + " payload: " + message.getPayload()));
	}
}
