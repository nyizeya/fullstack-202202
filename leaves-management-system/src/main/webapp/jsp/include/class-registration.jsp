<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-hover">
	<thead>
		<tr>
			<th>Registration Date</th>
			<th>Student</th>
			<th>Phone</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ dto.registrations }" var="item">
			<tr>
				<td>${ item.startDate }</td>
				<td>${ item.student }</td>
				<td>${ item.studentPhone }</td>
				<td>
					<c:url value="/classes/registration" var="edit">
						<c:param name="classId" value="${ item.classId }"></c:param>
						<c:param name="studentId" value="${ item.studentId }"></c:param>
						<c:param name="teacherName" value="${ dto.classInfo.teacherName }"></c:param>
						<c:param name="startDate" value="${ dto.classInfo.startDate }"></c:param>
					</c:url>
					<a href="${edit}"><i class="bi bi-pencil-square me-3"></i></a>
					
					<c:url value="/classes/registration/${ item.classId }/${ item.studentId }" var="details">
					</c:url>
					<a href="${details}"><i class="bi bi-cursor"></i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>