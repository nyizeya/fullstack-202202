package com.jdc.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.listener.SpringContainerManager;
import com.jdc.assignment.model.CourseModel;

@WebServlet(urlPatterns = { "/", "/courses", "/course-save" })
public class CourseServlet extends AbstractBeanFactroyServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = switch (req.getServletPath()) {
		case "/course-edit" -> "/course-edit.jsp";
		default -> {
			// load course and set results in request scope
			var model = getBean("courseModel", CourseModel.class);
			req.setAttribute("courses", model.getAll());
			yield "/index.jsp";
		}
		};
		
		getServletContext().getRequestDispatcher(page).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// get request parameters
		String name = req.getParameter("name");
		int fees = Integer.parseInt(req.getParameter("fees"));
		int duration = Integer.parseInt(req.getParameter("duration"));
		String description = req.getParameter("description");
		
		// create course object
		var course = new Course();
		course.setName(name);
		course.setFees(fees);
		course.setDuration(duration);
		course.setDescription(description);
		
		// insert into database
		getBean("courseModel", CourseModel.class).save(course);
		
		
		// redirect to top page
		resp.sendRedirect("/");
	}

}
