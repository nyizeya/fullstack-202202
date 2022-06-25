package com.jdc.project.model.dto;

import java.time.LocalDate;

public interface ProjectVO {
	
	int getId();
	String getName();
	String getDescription();
	int getManagerId();
	String getManagerName();
	String getManagerLogin();
	LocalDate getStartDate();
	int getMonths();

}
