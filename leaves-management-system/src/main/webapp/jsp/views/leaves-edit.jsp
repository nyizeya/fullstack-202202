<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leaves | Leaves</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	
	<c:import url="/jsp/include/navbar.jsp">
		<c:param name="view" value="leaves"></c:param>
	</c:import>
	
	<div class="container">
		<h3 class="my-4">Leave Application</h3>
		
		<div class="row">
			<c:url value="/leaves" var="save"></c:url>
			<sf:form method="post" action="${ save }" modelAttribute="leaveForm" cssClass="col-6" >
				<sf:hidden path="classId"/>
				<sf:hidden path="student"/>
				<sf:hidden path="applyDate"/>
				
				<div class="mb-3">
					<label for="" class="form-label">Class</label>
					<span class="form-control">${ classInfo.description }</span>
				</div>
				
				<div class="mb-3">
					<label for="" class="form-label">Teacher</label>
					<span class="form-control">${ classInfo.teacherName }</span>
				</div>
				
				<div class="mb-3">
					<label for="" class="form-label">Student</label>
					<span class="form-control">${ studentInfo.name }</span>
				</div>
				
				<div class="mb-3">
					<label for="" class="form-label">Leaves Start Date</label>
					<sf:input path="startDate" type="date" cssClass="form-control" />
					<sf:errors path="startDate" cssClass="text-warning"></sf:errors>
				</div>
				
				<div class="mb-3">
					<label for="" class="form-label">Days</label>
					<sf:input path="days" type="number" cssClass="form-control"/>
					<sf:errors path="days" cssClass="text-warning"></sf:errors>
				</div>
				
				<div class="mb-3">
					<label for="" class="form-label">Reason of Leaves</label>
					<sf:textarea path="reason" type="text" cssClass="form-control"/>
					<sf:errors path="reason" cssClass="text-warning"></sf:errors>
				</div>
				
				<div>
					<button class="btn btn-outline-success"><i class="bi bi-save"></i> Save</button>
				</div>
				
			</sf:form>
			
		</div>
	</div>
	
</body>
</html>