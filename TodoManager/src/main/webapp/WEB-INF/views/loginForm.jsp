<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/common/header.jsp"%>

<section>
	<h1>회원 로그인페이지</h1>
	<form action="/shop/login" method='post'>
		<div class="error">${error }</div>
		<table border=1>
			<tr>
				<th>아이디</th>
				<th><input type='text' name="member_id"></th>
			</tr>
			<tr>
				<th>비밀번호</th>
				<th><input type='password' name="member_pw"></th>
			</tr>
			<tr>
				<th><input type='submit' value='로그인'></th>
			</tr>
		</table>
	</form>
</section>

<%@ include file="/common/footer.jsp"%>

<script>
	
</script>