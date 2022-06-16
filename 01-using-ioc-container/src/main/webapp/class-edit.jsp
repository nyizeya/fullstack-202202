<%@page import="com.jdc.assignment.domain.Course"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course Edit</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container mt-4">
		<h1 class="text-center">Using IOC Container</h1>
		
		
			<h3 class="text-center">Classes for ${ course.name }</h3>
			
			<h5 class="text-center"><a href="/">Home</a></h5>
		
		<div class="row mt-3 ">
			<div class="col-4 mx-auto">
				<c:url var="addNew" value="/classes"></c:url>
				<form action="${ addNew }" method="post">
					<div class="mb-3">
						<label for="" class="form-label">Course ID</label>
						<input type="text" readonly="readonly" value="${ course.id }" placeholder="Enter Course ID" required="required" name="courseId" class="form-control" />
					</div>
					
					<div class="mb-3">
						<label for="" class="form-label">Start Date</label>
						<input type="date" required="required" name="startDate" class="form-control" id="" />
					</div>
					
					<div class="mb-3">
						<label for="" class="form-label">Teacher</label>
						<input type="text" name="teacher" required="required" placeholder="Enter Teacher Name" class="form-control" id="" />
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