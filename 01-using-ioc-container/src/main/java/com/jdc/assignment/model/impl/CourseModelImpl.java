package com.jdc.assignment.model.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.model.CourseModel;

public class CourseModelImpl implements CourseModel {

	private static final String SELECT_ALL = "select * from course";
	private static final String INSERT = "insert into course (name, fees, duration, description) values (?, ?, ?, ?)";
	private static final String FIND_BY_ID = "select * from course where id = ?";
	private DataSource dataSource;

	public CourseModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Course> getAll() {
		var list = new ArrayList<Course>();

		try (var connection = dataSource.getConnection(); var statement = connection.prepareStatement(SELECT_ALL)) {

			var result = statement.executeQuery();

			while (result.next()) {
				var course = new Course();

				course.setId(result.getInt("id"));
				course.setName(result.getString("name"));
				course.setFees(result.getInt("fees"));
				course.setDuration(result.getInt("duration"));
				course.setDescription(result.getString("description"));

				list.add(course);
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}

		return list;
	}

	@Override
	public void save(Course course) {
		try (var connection = dataSource.getConnection(); var statement = connection.prepareStatement(INSERT)) {

			statement.setString(1, course.getName());
			statement.setInt(2, course.getFees());
			statement.setInt(3, course.getDuration());
			statement.setString(4, course.getDescription());

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
		}

	}

	@Override
	public Course findById(int id) {

		try (var connection = dataSource.getConnection(); var statement = connection.prepareStatement(FIND_BY_ID)) {
			
			statement.setInt(1, id);
			
			var result = statement.executeQuery();

			while (result.next()) {
				var course = new Course();

				course.setId(result.getInt("id"));
				course.setName(result.getString("name"));
				course.setFees(result.getInt("fees"));
				course.setDuration(result.getInt("duration"));
				course.setDescription(result.getString("description"));

				return course;
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}

		return null;
	}

}
