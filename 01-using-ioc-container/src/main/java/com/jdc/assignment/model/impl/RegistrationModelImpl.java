package com.jdc.assignment.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.domain.Registration;
import com.jdc.assignment.model.RegistrationModel;

public class RegistrationModelImpl implements RegistrationModel {

	private static final String FIND_BY_ID = """
			select re.id, re.open_class_id, re.student, re.phone, re.email,
			oc.course_id, oc.start_date, oc.teacher,
			c.name course_name, c.fees, c.duration, c.description
			from registration re inner join open_class oc 
			on re.open_class_id = oc.id 
			inner join course c
			on oc.course_id = c.id where oc.id = ?
			""";

	private static final String CREATE_REGISTRATION = """
			insert into registration (open_class_id, student, phone, email)
			values (?, ?, ?, ?)
			""";
	
	private DataSource dataSource;
	
	public RegistrationModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Registration> findByOpenClass(int openClassId) {
		
		var list = new ArrayList<Registration>();
		
		try (var connection = dataSource.getConnection();
				var statement = connection.prepareStatement(FIND_BY_ID)) {
			
			statement.setInt(1, openClassId);
			
			var result = statement.executeQuery();
			
			while (result.next()) {
				// create course
				var course = new Course();
				course.setId(result.getInt("course_id"));
				course.setName(result.getString("course_name"));
				course.setFees(result.getInt("fees"));
				course.setDuration(result.getInt("duration"));
				course.setDescription(result.getString("description"));
				
				// create open class
				var openClass = new OpenClass();
				openClass.setId(result.getInt("open_class_id"));
				openClass.setCourse(course);
				openClass.setStartDate(result.getDate("start_date").toLocalDate());
				openClass.setTeacher(result.getString("teacher"));
				
				// create registration
				var registration = new Registration();
				registration.setId(result.getInt("id"));
				registration.setOpenClass(openClass);
				registration.setStudent(result.getString("student"));
				registration.setPhone(result.getString("phone"));
				registration.setEmail(result.getString("email"));
				
				list.add(registration);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}

	@Override
	public void create(Registration registration) {
		
		try (var connection = dataSource.getConnection();
				var statement = connection.prepareStatement(CREATE_REGISTRATION)) {
			
			statement.setInt(1, registration.getOpenClass().getId());
			statement.setString(2, registration.getStudent());
			statement.setString(3, registration.getPhone());
			statement.setString(4, registration.getEmail());
			
			statement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
