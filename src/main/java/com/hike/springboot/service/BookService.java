package com.hike.springboot.service;

import com.hike.springboot.bean.BookEntity;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-04-04 10:39
 * @desc 监听消息对列book的消息
 **/
@Service
public class BookService {

	@RabbitListener(queues = "atguigu.news")
	public void receive(BookEntity book){
		System.out.println (book);
	}

	@RabbitListener(queues = "atguigu")
	public void receive02(Message message){
		System.out.println (message.getBody ());
		System.out.println (message.getMessageProperties ());
	}
}
