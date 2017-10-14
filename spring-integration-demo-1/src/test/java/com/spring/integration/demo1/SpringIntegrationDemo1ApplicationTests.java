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
	
	private Message<String> messageHolaMundo = MessageBuilder.withPayload("HOLA MUNDO").build();
	
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
	public void test1() {
		template.send(directChannel, messageHolaMundo);
	}

	@Test
	public void test2(){
		template.send(queueChannel, messageHolaMundo);
		
		Message<?> message = template.receive(queueChannel);
		System.out.println("message: " + (message == null ? message : message.getPayload()));
	}
}
