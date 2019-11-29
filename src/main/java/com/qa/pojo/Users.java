package com.qa.pojo;

import lombok.Getter;

//pojo - plain old java object
public class Users {

	@Getter
	String name;
	
	@Getter
	String job;

	public Users(String name, String job) {
		super();
		this.name = name;
		this.job = job;
	}
	
	/*public String getName() {
		return name;
	}
	public String getJob() {
		return job;
	}*/
	
}
