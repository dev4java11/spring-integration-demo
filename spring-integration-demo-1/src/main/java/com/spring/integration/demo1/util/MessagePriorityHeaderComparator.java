package com.spring.integration.demo1.util;

import java.util.Comparator;

import org.springframework.messaging.Message;

public class MessagePriorityHeaderComparator implements Comparator<Message<?>> {

	@Override
	public int compare(Message<?> o1, Message<?> o2) {
		Integer priority1 = (Integer) o1.getHeaders().getOrDefault("priority", new Integer(0));
		Integer priority2 = (Integer) o2.getHeaders().getOrDefault("priority", new Integer(0));	
		return priority1.compareTo(priority2);
	}
}
