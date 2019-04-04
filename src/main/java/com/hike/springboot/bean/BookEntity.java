package com.hike.springboot.bean;

import lombok.Data;


/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-04-01 16:02
 * @desc 测试序列化的对象
 **/
@Data
public class BookEntity {

	private String bookName;
	private String author;


	public BookEntity () {
	}

	public BookEntity (String bookName, String author) {
		this.bookName = bookName;
		this.author = author;
	}


}
