package com.jdc.leaves.model.dto.input;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class ClassForm {

	public ClassForm() {
		super();
	}

	public ClassForm(int id, int teacher, LocalDate start, int months, String description) {
		super();
		this.id = id;
		this.teacher = teacher;
		this.start = start;
		this.months = months;
		this.description = description;
	}

	private int id;
	
	@Min(value = 1, message = "Please Select Teacher")
	private int teacher;
	
	@NotNull(message = "Please Choose Start Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate start;
	
	@Min(value = 1,message = "Please Select Duration")
	private int months;

	@NotEmpty(message = "Please Select Description")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacher() {
		return teacher;
	}

	public void setTeacher(int teacher) {
		this.teacher = teacher;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}