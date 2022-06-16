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
		<h3 class="text-center">Add New Course</h3>
		
		<h5 class="text-center"><a href="/">Home</a></h5>
		
		<div class="row mt-3 ">
			<div class="col-4 mx-auto">
				<c:url var="save" value="/courses"></c:url>
				<form action="${ save }" method="post">
					<div class="mb-3">
						<label for="" class="form-label">Name</label>
						<input type="text" placeholder="Enter Course Name" required="required" name="name" class="form-control" />
					</div>
					<div class="mb-3">
						<label for="" class="form-label">Fees</label>
						<input type="number" placeholder="Enter Course Fees" required="required" name="fees" class="form-control" />
					</div>
					<div class="mb-3">
						<label for="" class="form-label">Duration</label>
						<input type="number" placeholder="Enter Course Duration" required="required" name="duration" class="form-control" />
					</div>
					<div class="mb-3">
						<label for="" class="form-label">Description</label>
						<textarea rows="4" name="description" required="required" class="form-control" cols="40"></textarea>
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