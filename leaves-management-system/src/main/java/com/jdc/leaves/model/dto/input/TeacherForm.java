package com.jdc.leaves.model.dto.input;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class TeacherForm {

	public TeacherForm() {
		super();
	}

	public TeacherForm(int id, String name, String phone, String email, LocalDate assignDate) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.assignDate = assignDate;
	}

	private int id;

	@NotEmpty(message = "Please Enter Name")
	private String name;

	@NotEmpty(message = "Please Enter Phone")
	private String phone;

	@NotEmpty(message = "Please Enter Email")
	private String email;

	@NotNull(message = "Please Select Assign Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate assignDate;

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

}