<%@page import="java.util.List"%>
<%@page import="com.jdc.assignment.model.OpenClassModel"%>
<%@page import="com.jdc.assignment.domain.Course"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Edit</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container mt-4">
		<h1 class="text-center">Using IOC Container</h1>
		
		
			<h3 class="text-center">Registration</h3>
			
			<h5 class="text-center"><a href="/">Home</a></h5>
		
		<div class="row mt-3 ">
			<div class="col-4 mx-auto">
				<c:url var="addNew" value="/registrations"></c:url>
				<form action="${ addNew }" method="post">
					<div class="mb-3">
						<label class="form-label">Course ID</label>
						<input type="text" readonly="readonly" value="${ course.id }" required="required" name="courseId" class="form-control" />
					</div>
					
					<div class="mb-3">
						<label class="form-label">Open Class ID</label>
						<input type="text" readonly="readonly" value="${ openClassId }" required="required" name="openClassId" class="form-control" />
					</div>
					
					<div class="mb-3">
						<label  class="form-label">Teacher</label>
						<select name="teacher" id="" class="form-control">
							<c:forEach var="oc" items="${ openClass }">
								<option value="${ oc.teacher }">${ oc.teacher }</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="mb-3">
						<label class="form-label">Start Date</label>
						<input type="date" name="startDate"  class="form-control" required="required" />
					</div>
					
					<div class="mb-3">
						<label class="form-label">Student</label>
						<input type="text" name="student" required="required" placeholder="Enter Student Name" class="form-control"  />
					</div>
					
					<div class="mb-3">
						<label class="form-label">Student Phone</label>
						<input type="tel" name="phone" required="required" placeholder="Enter Student Phone" class="form-control"  />
					</div>
					
					<div class="mb-3">
						<label class="form-label">Student Email</label>
						<input type="email" name="email" required="required" placeholder="Enter Student Email" class="form-control"  />
					</div>
					
					
					<div class="mb-3 float-end">
						<input type="submit" value="Save" class="btn btn-primary" />
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>