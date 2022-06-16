package com.jdc.assignment.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.domain.Registration;
import com.jdc.assignment.model.CourseModel;
import com.jdc.assignment.model.RegistrationModel;
import com.jdc.assignment.model.impl.OpenClassModelImpl;

@WebServlet(urlPatterns = {
	"/registrations",
	"/registration-edit"
})
public class RegistrationServlet extends AbstractBeanFactroyServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		var openClassId = Integer.parseInt(req.getParameter("openClassId"));
		var courseId = Integer.parseInt(req.getParameter("courseId"));

		var openClassModel = getBean("openClassModel", OpenClassModelImpl.class);
		var openClass = openClassModel.findByCourse(courseId);
		var course = openClassModel.findOneCourse(getBean("courseModel", CourseModel.class), courseId);		
		
		
		req.setAttribute("openClass", openClass);
		req.setAttribute("course", course);
		req.setAttribute("openClassId", openClassId);
		
		var page = switch (req.getServletPath()) {
		case "/registrations" -> {
			var list = getBean("registrationModel", RegistrationModel.class).findByOpenClass(openClassId);
			req.setAttribute("registrations", list);
			
			yield "/registrations.jsp";
		}
		default -> {
			yield "/registration-edit.jsp";
		}
		};
		
		getServletContext().getRequestDispatcher(page).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var courseId = Integer.parseInt(req.getParameter("courseId"));
		var openClassId = Integer.parseInt(req.getParameter("openClassId"));
		var startDate = LocalDate.parse(req.getParameter("startDate"));
		var teacher = req.getParameter("teacher");
		var student = req.getParameter("student");
		var phone = req.getParameter("phone");
		var email = req.getParameter("email");
		
		var course = getBean("courseModel", CourseModel.class).findById(courseId);
		var openClass = new OpenClass();
		openClass.setId(openClassId);
		openClass.setCourse(course);
		openClass.setStartDate(startDate);
		openClass.setTeacher(teacher);
		
		var registration = new Registration();
		registration.setOpenClass(openClass);
		registration.setStudent(student);
		registration.setPhone(phone);
		registration.setEmail(email);
		
		getBean("registrationModel", RegistrationModel.class).create(registration);
		
		resp.sendRedirect("/");
	}

}
