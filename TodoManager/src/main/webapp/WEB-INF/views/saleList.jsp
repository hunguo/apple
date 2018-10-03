<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/common/header.jsp"%>

<section>
	<h1>일정 리스트</h1>
	
	<table border = 1>
		<tr>
			<th>ID</th>
			<th>SCHEDULE</th>
			<th>TIME</th>
			<th>CHECK</th>
		</tr>
		<c:forEach items="${sale }" var="todo">
		<tr>
			<td>${todo.member_id }</td>
			<td>${todo.sche_name}</td>
			<td>${todo.sche_time}</td>
			<td>${todo.sche_check}</td>
		</tr>
		</c:forEach>
	</table>

</section>

<%@ include file="/common/footer.jsp"%>

<script>
</script>