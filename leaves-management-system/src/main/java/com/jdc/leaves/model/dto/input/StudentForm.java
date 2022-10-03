package com.jdc.leaves.model.dto.input;

public class StudentForm {

	public StudentForm(int id, String name, String phone, String email, String education) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.education = education;
	}

	public StudentForm(String name, String phone, String email, String education) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.education = education;
	}

	private int id;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	private String education;
	
	private int classCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public int getClassCount() {
		return classCount;
	}

	public void setClassCount(int classCount) {
		this.classCount = classCount;
	}

}
