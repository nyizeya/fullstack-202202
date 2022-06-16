package com.jdc.assignment.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.model.CourseModel;
import com.jdc.assignment.model.OpenClassModel;

@WebServlet(urlPatterns = {
		"/classes",
		"/class-edit"
})
public class OpenClassServlet extends AbstractBeanFactroyServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int courseId = Integer.parseInt(req.getParameter("courseId"));
		
		// find course
		var courseModel = getBean("courseModel", CourseModel.class);
		var course = courseModel.findById(courseId);
		
		req.setAttribute("course", course);
		
		var page = switch (req.getServletPath()) {
		case "/classes" -> {
			var list = getBean("openClassModel", OpenClassModel.class).findByCourse(courseId);
			req.setAttribute("classes", list);
			yield "/classes.jsp";
		}
		default -> {
			yield "/class-edit.jsp";
		}
		};
		
		getServletContext().getRequestDispatcher(page).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// get request parameters
		var courseId = Integer.parseInt(req.getParameter("courseId"));
		var startDate = LocalDate.parse(req.getParameter("startDate"));
		var teacher = req.getParameter("teacher");
		
		var course = getBean("courseModel", CourseModel.class).findById(courseId);
		var openClass = new OpenClass();
		
		openClass.setCourse(course);
		openClass.setStartDate(startDate);
		openClass.setTeacher(teacher);
		
		getBean("openClassModel", OpenClassModel.class).create(openClass);
		
		resp.sendRedirect("/");
		
	}
	
}
