<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="doModify" method="POST">
		<h1><%=(int) articleRow.get("id")%>번 게시물 수정
		</h1>

		<div>
			번호 : <input type="hidden" name="id" value=<%=(int) articleRow.get("id")%> />
			</div>
		<div>
			날짜 :
			<%=(LocalDateTime) articleRow.get("regDate")%></div>
		<div>
			제목 : <input name="title"  type="text" value=<%=(String)articleRow.get("title") %>/>
			</div>
		<div>
			내용 : <input name="body"  type="text" value=<%=(String) articleRow.get("body")%>/>
			</div>
			<button type='submit'>수정</button>
		<div>
			<a href="list">목록</a>
		</div>

	</form>
</body>
</html>