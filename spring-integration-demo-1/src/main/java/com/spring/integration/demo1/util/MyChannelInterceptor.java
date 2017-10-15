package com.spring.integration.demo1.util;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

public class MyChannelInterceptor extends ChannelInterceptorAdapter {

	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		if(sent){
			System.out.println("postSend -> Message "+message+" send to channel "+channel);
		}else{
			System.out.println("postSend -> Message "+message+" not send to channel "+channel);
		}
	}
	
	@Override
	public Message<?> postReceive(Message<?> message, MessageChannel channel) {
		System.out.println("postReceive -> Message "+message+" receive from channel "+channel);
		return message;
	}
}
