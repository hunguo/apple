<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/common/header.jsp"%>

<section>
	<h1>회원 정보 수정</h1>
	
	<form id='update_form' onsubmit='return update()' method='post'>
		<table>
			<caption class="error"></caption>
			<tr>
				<td><label for='member_id'>회원아이디: </label></td>
				<td>
					<input id='mem_id' type='text' name='member_id' 
						value='${member.member_id }' readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input id='mem_pw1' type='password' name='member_pw'
					placeholder="Enter Password" required></td>
			</tr>
			
			<tr>
				<td>회원성명</td>
				<td><input id='mem_name' type='text' name='member_name'
					value='${member.member_name }' required></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input id='mem_email' type='text' name='member_email'
					value='${member.member_email }' required></td>
			</tr>
			<tr>
				<td colspan=2 style="text-align: center">
					<input id='update_button' type='submit' value='수정'>
					<input id='cancle_button' type='button'  value='취소' onclick='cancel()'>
				</td>
			</tr>
		</table>
	</form>

</section>

<%@ include file="/common/footer.jsp"%>

<script>
var request;
function update() {
	console.log('update called');
	
	var pw1 = document.getElementById('mem_pw1')
	if (pw1 == '') {
		alert('비밀번호를 입력하세요')
		return false;
	} 
	
	var params = '';
	var formObj = document.getElementById('update_form');
	request = new XMLHttpRequest();
	var url = "${pageContext.request.contextPath}/update";
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
		var elems = document.getElementsByClassName('error');
		
		if (result == 'success') {
			elems[0].innerText = "";
			alert('업데이트 성공')
		} else if (result == 'fail') {
			
			elems[0].innerText = "비밀번호가 틀렸습니다"
		} 
		else {
			elems[0].innerText = "";
			alert('업데이트 실패(내부적 문제), 잠시후 다시 시도하세요')
		}
	}
}
</script>