package com.spring.integration.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.handler.MethodInvokingMessageHandler;

import com.spring.integration.demo1.invoker.SuscriberInvoker;

@SpringBootApplication
public class SpringIntegrationDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationDemo1Application.class, args);
	}
	
	@Bean
	public MessagingTemplate messagingTemplate(){
		MessagingTemplate template = new MessagingTemplate();
		return template;
	}
	
	@Bean
	public MethodInvokingMessageHandler suscriberInvoker(){
		MethodInvokingMessageHandler invoker = new MethodInvokingMessageHandler(new SuscriberInvoker(), SuscriberInvoker.class.getMethods()[0]);
		return invoker;
	}
	
	@Bean
	public DirectChannel directChannel(){
		DirectChannel channel = new DirectChannel();
		channel.subscribe(suscriberInvoker());
		return channel;
	}
	
	@Bean
	public QueueChannel queueChannel(){
		QueueChannel channel = new QueueChannel();
		return channel;
	}
}
