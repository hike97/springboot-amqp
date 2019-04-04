package com.hike.springboot;

import com.hike.springboot.bean.Beauty;
import com.hike.springboot.bean.BookEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith (SpringRunner.class)
@SpringBootTest
public class SpringbootAmqpApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void test_createExchange() {
		amqpAdmin.declareExchange (new DirectExchange ("amqpadmin.exchange"));
		amqpAdmin.declareQueue (new Queue ("amqpadmin.queue",true));
		amqpAdmin.declareBinding (new Binding ("amqpadmin.queue", Binding.DestinationType.QUEUE,"amqpadmin.exchange","amq.route",null));
	}

	@Test
	public void test_sendMsg () {
		BookEntity entity = new BookEntity ("明朝那些事", "我愚蠢的弟弟哟");
		rabbitTemplate.convertAndSend ("exchange.fanout","",entity);
	}
	/**
	 * 发送自定义实体类
	 */
	@Test
	public void test_sendObject () {
		BookEntity entity = new BookEntity ("Think in java", "JamesGolin");
//		Beauty entity = new Beauty ("如花", "18");
		rabbitTemplate.convertAndSend ("exchange.direct","atguigu.news",entity);
	}

	/**
	 * 1.单播（点对点）
	 */
	@Test
	public void contextLoads () {
		//Message需要自己构造一个；定义消息体内容和消息头
		//rabbitTemplate.send (exchange,routeKey,messge);
		//object默认成消息体，只需要传入要发送的对象，自动序列化发给rabbitmq
	   //rabbitTemplate.convertAndSend (exchange,routeKey,object);
		Map<Object, Object> map = new HashMap<> ();
		map.put ("msg","this is my first message");
		map.put ("data", Arrays.asList ("hehe",123,true));
		rabbitTemplate.convertAndSend ("exchange.direct","atguigu.news",map);
		/*
		 *  content_type:	application/x-java-serialized-object
			Payload
			362 byte
			Encoding: base64
			rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAACdAADbXNndAAY
			dGhpcyBpcyBteSBmaXJzdCBtZXNzYWdldAAEZGF0YXNyABpqYXZhLnV0aWwuQXJyYXlzJEFycmF5TGlzdNmkPL7NiAbSAgABWwABYXQAE1tMamF2YS9sYW5n
			L09iamVjdDt4cHVyABdbTGphdmEuaW8uU2VyaWFsaXphYmxlO67QCaxT1+1JAgAAeHAAAAADdAAEaGVoZXNyABFqYXZhLmxhbmcuSW50ZWdlchLioKT3gYc4
			AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAAB7c3IAEWphdmEubGFuZy5Cb29sZWFuzSBygNWc+u4CAAFaAAV2YWx1ZXhw
			AXg=
		 */
		//发出的文案 和默认消息头

		/*
			Properties
			priority:	0
			delivery_mode:	2
			headers:
			__ContentTypeId__:	java.lang.Object
			__KeyTypeId__:	java.lang.Object
			__TypeId__:	java.util.HashMap
			content_encoding:	UTF-8
			content_type:	application/json
			Payload
			59 bytes
			Encoding: string
			{"msg":"this is my first message","data":["hehe",123,true]}
		 */
		//修改为json转换后的请求体
	}

	//接收数据，如何将数据自动转为json直接发送出去
	@Test
	public void test_receiveMsg () {
		Object o = rabbitTemplate.receiveAndConvert ("atguigu.news");
		System.out.println (o.getClass ());
		System.out.println (o);
	}
	/*
		class java.util.HashMap
		{msg=this is my first message, data=[hehe, 123, true]}
	 */

}
