package com.jdc.leaves.model.dto.output;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.jdc.leaves.model.dto.input.TeacherForm;

public class TeacherListVO {

	public TeacherListVO() {
	}

	private int id;

	private String name;

	private String phone;

	private String email;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate assignDate;

	private long classCount;

	public TeacherForm form() {
		return new TeacherForm(id, name, phone, email, assignDate);
	}
	
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

	public LocalDate getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(LocalDate assignDate) {
		this.assignDate = assignDate;
	}

	public long getClassCount() {
		return classCount;
	}

	public void setClassCount(long classCount) {
		this.classCount = classCount;
	}

}