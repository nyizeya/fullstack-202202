package com.jdc.leaves.model.dto.input;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

public class RegistrationForm {

	public RegistrationForm() {
		super();
	}

	public RegistrationForm(int classId, int studentId, LocalDate registDate, String studentName, String email, String phone,
			String education) {
		super();
		this.classId = classId;
		this.studentId = studentId;
		this.registDate = registDate;
		this.studentName = studentName;
		this.email = email;
		this.phone = phone;
		this.education = education;
	}

	private int classId;
	private int studentId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate registDate;

	@NotEmpty(message = "Please Enter Student Name")
	private String studentName;

	@NotEmpty(message = "Please Enter Email Address")
	private String email;

	@NotEmpty(message = "Please Enter Phone Number")
	private String phone;

	private String education;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public LocalDate getRegistDate() {
		return registDate;
	}

	public void setRegistDate(LocalDate registDate) {
		this.registDate = registDate;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	public StudentForm studentForm() {
		return new StudentForm(studentId, studentName, phone, email, education);
	}

}