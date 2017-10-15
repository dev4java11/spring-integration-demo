package com.spring.integration.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.channel.RendezvousChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.handler.MethodInvokingMessageHandler;

import com.spring.integration.demo1.invoker.SuscriberInvoker;
import com.spring.integration.demo1.util.MessagePriorityHeaderComparator;
import com.spring.integration.demo1.util.MyChannelInterceptor;

@SpringBootApplication
public class SpringIntegrationDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationDemo1Application.class, args);
	}
	
	/** Template **/
	
	@Bean
	public MessagingTemplate messagingTemplate(){
		MessagingTemplate template = new MessagingTemplate();
		return template;
	}
	
	/** DIRECT CHANNEL **/
	
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
	
	/** QUEUE CHANNEL**/
	
	@Bean
	public QueueChannel queueChannel(){
		QueueChannel channel = new QueueChannel();
		return channel;
	}
	
	/** PUBLISH SUSCRBIER CHANNEL **/
	
	@Bean
	public MethodInvokingMessageHandler suscriberInvoker1(){
		MethodInvokingMessageHandler invoker = new MethodInvokingMessageHandler(new SuscriberInvoker(1), SuscriberInvoker.class.getMethods()[0]);
		return invoker;
	}
	
	@Bean
	public MethodInvokingMessageHandler suscriberInvoker2(){
		MethodInvokingMessageHandler invoker = new MethodInvokingMessageHandler(new SuscriberInvoker(2), SuscriberInvoker.class.getMethods()[0]);
		return invoker;
	}
	
	@Bean
	public MethodInvokingMessageHandler suscriberInvoker3(){
		MethodInvokingMessageHandler invoker = new MethodInvokingMessageHandler(new SuscriberInvoker(3), SuscriberInvoker.class.getMethods()[0]);
		return invoker;
	}
	
	@Bean
	public PublishSubscribeChannel publishSubscribeChannel(){
		PublishSubscribeChannel channel = new PublishSubscribeChannel();
		channel.setApplySequence(true);
		channel.subscribe(suscriberInvoker1());
		channel.subscribe(suscriberInvoker2());
		channel.subscribe(suscriberInvoker3());
		return channel;
	}
	
	/** EXECUTOR CHANNEL **/
	
	@Bean
	public MethodInvokingMessageHandler executorSuscriberInvoker(){
		MethodInvokingMessageHandler invoker = new MethodInvokingMessageHandler(new SuscriberInvoker(), SuscriberInvoker.class.getMethods()[0]);
		return invoker;
	}
	
	@Bean
	public TaskExecutor taskExecutor(){
		SyncTaskExecutor executor = new SyncTaskExecutor();
		return executor;
	}
	
	@Bean
	public ExecutorChannel executorChannel(){
		ExecutorChannel channel = new ExecutorChannel(taskExecutor());
		channel.subscribe(executorSuscriberInvoker());
		return channel;
	}
	
	/** PRIORITY CHANNEL **/
	
	@Bean
	public MessagePriorityHeaderComparator messagePriorityHeaderComparator(){
		MessagePriorityHeaderComparator comparator = new MessagePriorityHeaderComparator();
		return comparator;
	}
	
	@Bean
	public PriorityChannel priorityChannel(){
		PriorityChannel channel = new PriorityChannel(messagePriorityHeaderComparator());
		return channel;
	}
	
	/** RENDEZVOUS CHANNEL **/
	
	@Bean
	public RendezvousChannel rendezvousChannel(){
		RendezvousChannel channel = new RendezvousChannel();
		return channel;
	}
	
	/** CHANNEL INTERCEPTOR **/
	
	@Bean
	public MyChannelInterceptor myChannelInterceptor(){
		MyChannelInterceptor interceptor = new MyChannelInterceptor();
		return interceptor;
	}
	
	@Bean
	public DirectChannel directChannelWithInterceptor(){
		DirectChannel channel = new DirectChannel();
		channel.subscribe(suscriberInvoker());
		channel.addInterceptor(myChannelInterceptor());
		return channel;
	}
}
