package com.hike.springboot.bean;

import java.io.Serializable;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-04-01 17:11
 * @desc
 **/
public class Beauty implements Serializable {

	private String name;
	private String age;

	public Beauty () {
	}

	public Beauty (String name, String age) {
		this.name = name;
		this.age = age;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getAge () {
		return age;
	}

	public void setAge (String age) {
		this.age = age;
	}
}
