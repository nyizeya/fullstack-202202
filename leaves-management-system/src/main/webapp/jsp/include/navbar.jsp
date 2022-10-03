<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url value="/home" var="home" />
<c:url value="/teachers" var="teachers" />
<c:url value="/students" var="students" />
<c:url value="/leaves" var="leaves" />
<c:url value="/classes" var="classes" />
<c:url value="/signout" var="signout" />

<c:url value="/resources/logout.js" var="logoutJS"></c:url>
<script src="${ logoutJS }"></script>

<c:url value="/resources/application.css" var="commonCSS"></c:url>
<link rel="stylesheet" href="${ commonCSS }" />

<nav class="navbar navbar-expand-lg bg-success navbar-dark">
	<div class="container">
		<a class="navbar-brand" href="${home}">Leaves Management System</a>

		<div class="navbar-nav">
			<sec:authorize access="hasAuthority('Admin')">
				<a class="nav-link ${ param.view eq 'teachers' ? 'active' : ''  }" href="${teachers}">
					<i class="bi bi-people"></i> Teachers 
				</a> 
			</sec:authorize>
			
			<sec:authorize access="hasAnyAuthority('Admin', 'Teacher')">
				<a class="nav-link ${ param.view eq 'classes' ? 'active' : '' }" href="${classes}"> 
					<i class="bi bi-mortarboard"></i> Classes 
				</a> 
				<a class="nav-link ${ param.view eq 'students' ? 'active' : '' }" href="${students}"> 
					<i class="bi bi-people"></i> Students 
				</a> 
			</sec:authorize>
			
			<sec:authorize access="hasAuthority('Student')">
				<a class="nav-link ${ param.view eq 'leaves' ? 'active' : '' }" href="${leaves}"> 
					<i class="bi bi-inbox"></i> Leaves 
				</a> 
			</sec:authorize>
			
			<a class="nav-link myBtn" id="logoutBtn"> 
				<i class="bi bi-box-arrow-left"></i> Sign Out 
			</a>
		</div>
	</div>

</nav>

<sf:form id="logoutForm" action="${signout}" method="post" cssClass="d-none">
	
</sf:form>