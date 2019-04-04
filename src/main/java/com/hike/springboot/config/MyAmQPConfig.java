package com.hike.springboot.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-04-01 15:50
 * @desc amqp自定义配置
 **/
@Configuration
public class MyAmQPConfig {

	@Bean
	public MessageConverter messageConverter(){
		return new Jackson2JsonMessageConverter ();
	}

}
