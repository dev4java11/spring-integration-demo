package com.spring.integration.demo1;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringIntegrationDemo1ApplicationTests {
	
	@Autowired
	private MessagingTemplate template;
	
	@Autowired
	@Qualifier("directChannel")
	private MessageChannel directChannel;
	
	@Autowired
	@Qualifier("queueChannel")
	private MessageChannel queueChannel;
	
	@Autowired
	@Qualifier("publishSubscribeChannel")
	private MessageChannel publishSubscribeChannel;
	
	@Autowired
	@Qualifier("executorChannel")
	private MessageChannel executorChannel;
	
	@Autowired
	@Qualifier("priorityChannel")
	private MessageChannel priorityChannel;
	
	@Autowired
	@Qualifier("rendezvousChannel")
	private MessageChannel rendezvousChannel;
	
	@Autowired
	@Qualifier("directChannelWithInterceptor")
	private MessageChannel directChannelWithInterceptor;
	
	private Message<String> messageHelloWord = MessageBuilder.withPayload("HOLA MUNDO").build();
	
	@Before
	public void before(){
		System.out.println("***************************************************************************************************************************************************************************************************************************");
	}
	
	@After
	public void after(){
		System.out.println("***************************************************************************************************************************************************************************************************************************");
	}

	@Test
	@Ignore
	public void testDirectChannel() {
		template.send(directChannel, messageHelloWord);
	}

	@Test
	@Ignore
	public void testQueueChannel(){
		template.send(queueChannel, messageHelloWord);
		
		Message<?> message = template.receive(queueChannel);
		System.out.println("message: " + (message == null ? message : message.getPayload()));
	}
	
	@Test
	@Ignore
	public void testPublishSubscribeChannel(){
		template.send(publishSubscribeChannel, messageHelloWord);
	}
	
	@Test
	@Ignore
	public void testExecutorChannel(){
		template.send(executorChannel, messageHelloWord);
	}
	
	@Test
	@Ignore
	public void testPriorityChannel(){
		Message<String> message1 = MessageBuilder.withPayload("RED").setHeader("priority", new Integer(7)).build();
		Message<String> message2 = MessageBuilder.withPayload("BLUE").setHeader("priority", new Integer(6)).build();
		Message<String> message3 = MessageBuilder.withPayload("BLACK").setHeader("priority", new Integer(5)).build();
		Message<String> message4 = MessageBuilder.withPayload("GREEN").setHeader("priority", new Integer(4)).build();
		Message<String> message5 = MessageBuilder.withPayload("YELLOW").setHeader("priority", new Integer(3)).build();
		Message<String> message6 = MessageBuilder.withPayload("ORANGE").setHeader("priority", new Integer(2)).build();
		Message<String> message7 = MessageBuilder.withPayload("WHITE").setHeader("priority", new Integer(1)).build();
		
		template.send(priorityChannel, message1);
		template.send(priorityChannel, message2);
		template.send(priorityChannel, message3);
		template.send(priorityChannel, message4);
		template.send(priorityChannel, message5);
		template.send(priorityChannel, message6);
		template.send(priorityChannel, message7);
		
		Message<?> msg1 = template.receive(priorityChannel);
		Message<?> msg2 = template.receive(priorityChannel);
		Message<?> msg3 = template.receive(priorityChannel);
		Message<?> msg4 = template.receive(priorityChannel);
		Message<?> msg5 = template.receive(priorityChannel);
		Message<?> msg6 = template.receive(priorityChannel);
		Message<?> msg7 = template.receive(priorityChannel);
		
		System.out.println(msg1);
		System.out.println(msg2);
		System.out.println(msg3);
		System.out.println(msg4);
		System.out.println(msg5);
		System.out.println(msg6);
		System.out.println(msg7);
	}
	
	@Test
	@Ignore
	public void testRendezvousChannel(){
		template.send(rendezvousChannel, messageHelloWord);
		
		Message<?> message = template.receive(rendezvousChannel);
		System.out.println(message);
	}

	@Test
	public void testDirectChannelWithInterceptor(){
		template.send(directChannelWithInterceptor, messageHelloWord);
	}
}
