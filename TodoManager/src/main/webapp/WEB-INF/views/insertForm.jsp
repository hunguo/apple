<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ include file="/common/header.jsp"%>

<style>
	.error {
		color: red;
	}
</style>
<section>
	<h1>일정등록페이지</h1>
	<form id='reg_form' action="${pageContext.request.contextPath }/insert" method='post'>
		<table>
			<tr>
				<td><label for='member_id'>회원아이디: </label></td>
				<td>
					<input id='mem_id' type='text' name='member_id' value="${schedule.member_id}">
					<form:errors path="schedule.member_id" class="error"></form:errors>
				</td>
			</tr>
			<tr>
				<td>What</td>
				<td><input type='text' name='sche_name'
					placeholder="Enter schedule..." required>
				<form:errors path="schedule.sche_name" class="error"></form:errors>	
				</td>
			</tr>
		
			<tr>
				<td>Time</td>
				<td><input type='date' name='sche_time'
					value="${today }">
				<form:errors path="schedule.sche_time" class="error"></form:errors>	
				</td>
			</tr>
				<tr>
				<td>Check</td>
				<td><input type='text' name='sche_check'
					placeholder="Enter check" required>
				<form:errors path="schedule.sche_check" class="error"></form:errors>	
				</td>
			</tr>
			<tr>
				<td colspan=2 style="text-align: center">
					<input id='reg_button' type='submit' value='업데이트'>
				</td>
			</tr>
		</table>
	</form>

</section>

<%@ include file="/common/footer.jsp"%>

<script>
	var request;

		
		var params = '';
		var formObj = document.getElementById('reg_form');
		request = new XMLHttpRequest();
		var url = "${pageContext.request.contextPath}/insert";
		var elem = formObj.elements;
		
		for (var i = 0; i < elem.length; i++) {
			if (elem[i].tagName == "SELECT") {
	            value = elem[i].options[elem[i].selectedIndex].value;
	        } else {
	            value = elem[i].value;                
	        }
			
			if (value != '') {
				params += elem[i].name + '=' + encodeURIComponent(value) + '&';	
			}
			
		}
		
		console.log(params);
		
		try {
			request.onreadystatechange = getResult;
			request.open("POST", url, true);
			request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			request.send(params);
		} catch(e) {
			alert('서버로 요청이 실패');
		}
		
		return false;
	
	}
	function getResult() {
		if (request.readyState == 4) {
			var result = request.responseText;
			
			if (result == 'success') {
				alert('업데이트 성공')
			} else {
				alert('업데이트 실패')
			}
		}
	}
	
	function getIdCheckResult() {
		if (request.readyState == 4) {
			var result = request.responseText;
			
			if (result == 'available') {
				idCheckDone = true;
				
				alert('사용가능')
			} else {
				alert('이미 사용된 아이디 입니다. 다른 아이디를 쓰세요')
			}
		}
	}

</script>