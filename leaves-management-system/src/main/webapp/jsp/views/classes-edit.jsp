<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leaves | Classes</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<c:import url="/jsp/include/navbar.jsp">
		<c:param name="view" value="classes"></c:param>
	</c:import>

	<div class="container">
		<h3 class="my-4">${ param.id > 0 ? 'Edit' : 'Add New' }Class</h3>

		<div class="row">
			<div class="col-lg-6 col-md-9 col-sm-12">
				<c:url value="/classes" var="save"></c:url>
				<sf:form action="${save}" modelAttribute="classForm" method="post"
					cssClass="">
					<sf:hidden path="id" />

					<!-- teacher id -->
					<div class="mb-3">
						<label for="" class="form-label">Teacher</label>
						<sf:select path="teacher" items="${teachers}" itemLabel="name"
							itemValue="id" cssClass="form-select"></sf:select>
						<sf:errors path="teacher" cssClass="text-warning"></sf:errors>

					</div>
					<div class="row mb-3">
						<!-- start -->
						<div class="col">
							<label for="" class="form-label">Start Date</label>
							<sf:input path="start" type="date" cssClass="form-control" />
							<sf:errors path="start" cssClass="text-warning"></sf:errors>
						</div>
						<!-- months -->
						<div class="col">
							<label for="" class="form-label">Months</label>
							<sf:input path="months" type="number" cssClass="form-control" />
							<sf:errors path="months" cssClass="text-warning"></sf:errors>
						</div>
					</div>

					<!-- description -->
					<div class="mb-3">
						<label for="" class="form-label">Class Description</label>
						<sf:textarea path="description" cssClass="form-control" />
						<sf:errors path="description" cssClass="text-warning"></sf:errors>
					</div>

					<div class="">
						<button class="btn btn-outline-danger" type="submit">
							<i class="bi bi-save"></i> Save
						</button>
					</div>

				</sf:form>
			</div>
		</div>

	</div>

</body>
</html>