package com.jdc.assignment.model.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.model.CourseModel;
import com.jdc.assignment.model.OpenClassModel;

public class OpenClassModelImpl implements OpenClassModel {
	
	private static final String FIND_BY_ID = """
			select oc.id, oc.start_date, oc.teacher,
			c.id courseId, c.name, c.duration, c.fees, c.description 
			from open_class oc join course c on oc.course_id = c.id where c.id = ?
			""";
	private static final String CREATE = """
			insert into open_class (course_id, start_date, teacher) values (?, ?, ?)
			""";
	private DataSource dataSource;
	
	

	public OpenClassModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public Course findOneCourse(CourseModel courseModel, int id) {
		return courseModel.findById(id);
	}
	
	@Override
	public List<OpenClass> findByCourse(int courseId) {
		
		List<OpenClass> list = new ArrayList<>();
		
		try (var connection = dataSource.getConnection();
				var statement = connection.prepareStatement(FIND_BY_ID)) {
			
			statement.setInt(1, courseId);
			
			var result = statement.executeQuery();
			
			while (result.next()) {
				var course = new Course();
				
				course.setId(result.getInt("courseId"));
				course.setName(result.getString("name"));
				course.setFees(result.getInt("fees"));
				course.setDuration(result.getInt("duration"));
				course.setDescription(result.getString("description"));
				
				var openClass = new OpenClass();
				
				openClass.setId(result.getInt("id"));
				openClass.setCourse(course);
				openClass.setTeacher(result.getString("teacher"));
				openClass.setStartDate(result.getDate("start_date").toLocalDate());
				
				list.add(openClass);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}

	@Override
	public void create(OpenClass openClass) {
		
		try (var connection = dataSource.getConnection();
				var statement = connection.prepareStatement(CREATE)) {
			
			statement.setInt(1, openClass.getCourse().getId());
			statement.setDate(2, Date.valueOf(openClass.getStartDate()));
			statement.setString(3, openClass.getTeacher());
			
			statement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
