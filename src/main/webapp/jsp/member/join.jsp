<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>

	<script>
		function JoinForm_submit(form) {
			form.loginId.value = form.loginId.value.trim();

			if (form.loginId.value.length == 0) {
				alert('아이디를 입력해주세요')
				form.loginId.focus();
				return;
			}

			form.loginPw.value = form.loginPw.value.trim();

			if (form.loginPw.value.length == 0) {
				alert('비밀번호를 입력해주세요')
				form.loginPw.focus();
				return;
			}
			form.loginPwConfirm.value = form.loginPwConfirm.value.trim();

			if (form.loginPwConfirm.value.length == 0) {
				alert('비밀번호 확인을 입력해주세요')
				form.loginPwConfirm.focus();
				return;
			}
			
			
			form.userName.value = form.userName.value.trim();

			if (form.userName.value.length == 0) {
				alert('이름을 입력해주세요')
				form.userName.focus();
				return;
			}
			
			if(form.loginPwConfirm.value !=form.loginPw.value){
				alert('비밀번호가 일치하지 않습니다')
				form.loginPw.focus();
				return;
			}
			form.submit();
			
		}
	</script>

	<form action="doJoin" method="POST"
		onsubmit="JoinForm_submit(this); return false;">
		<div>
			아이디 : <input name="loginId" autocomplete="off" placeholer="아이디를 입력하세요" type="text" />
		</div>
		<div>
			비밀번호 : <input name="loginPw" placeholer="비밀번호를 입력해주세요"
				type="password" />
		</div>
		<div>
			로그인 비밀번호 확인 : <input name="loginPwConfirm"
				placeholer="비밀번호 확인을 입력해주세요" type="password" />
		</div>
		<div>
			이름 : <input name="userName" placeholer="이름을 입력해주세요" type="text" />
		</div>


		<button type="submit">작성</button>
		<a href="../home/main">취소</a>

	</form>
</body>
</html>