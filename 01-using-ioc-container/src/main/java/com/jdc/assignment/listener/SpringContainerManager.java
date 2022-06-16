package com.jdc.assignment.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.support.GenericXmlApplicationContext;

@WebListener
public class SpringContainerManager implements ServletContextListener {
	
	public static final String SPRING_CONTEXT = "spring.context";
	private GenericXmlApplicationContext springContext;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Start IOC Container
		System.out.println("IOC Container Initialized");
		
		// Add ioc container to application scope
		springContext = new GenericXmlApplicationContext("classpath:/application.xml");
		sce.getServletContext().setAttribute(SPRING_CONTEXT, springContext);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("IOC Container Destroyed");
		
		if (null != springContext) {
			springContext.close();
		}
	}

}
