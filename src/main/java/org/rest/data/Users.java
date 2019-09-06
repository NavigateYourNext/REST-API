package org.rest.data;

//Known as POJO (Plain Old Java Object)
public class Users 
{

	public String name;
	public String job;
	public String id;
	public String createdAt;

	public Users()
	{

	}

	public Users(String name, String job)
	{
		
		this.name = name;
		this.job = job;
		
	}
	
	//Generate Getters and Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public String getJob() {
		return job;
	}



}
