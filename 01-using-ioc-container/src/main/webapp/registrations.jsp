<%@page import="com.jdc.assignment.domain.Course"%>
<%@page import="com.jdc.assignment.model.CourseModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrations</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
	crossorigin="anonymous"></script>
</head>
<body>

	<div class="container mt-4">
		<h1>Using IOC Container</h1>

		<h3>Registration for ${ course.name }</h3>
		
		<h5><a href="/">Home</a></h5>

		<div>
			<a href="/registration-edit?courseId=${ course.id }&openClassId=${ openClassId }" class="btn btn-primary">Add New Registration</a>
		</div>


		<div class="mt-4">
			<c:choose>
				<c:when test="${ empty registrations }">
					<div class="alert alert-warning">There is no registration. Please
						create new registration.</div>
				</c:when>
				<c:otherwise>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Course</th>
								<th>Teacher</th>
								<th>Student</th>
								<th>Student Phone</th>
								<th>Student Email</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="r" items="${ registrations }">
								<tr>
									<td>${ r.openClass.course.name }</td>
									<td>${ r.openClass.teacher }</td>
									<td>${ r.student }</td>
									<td>${ r.phone }</td>
									<td>${ r.email }</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

</body>
</html>



<!-- 



 -->