<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>아 왜그러세요</h1>
	<ul>
		<li><input id="replyer" value="${replySelect.replyer}">
		<li><input id="reply" value="${replySelect.reply}"><button id="update">Update</button>
		<button id="list" onclick="location.href='/lookie/board/select?num=${replySelect.num}&page=${page}'">List</button>
	</ul>
</body>
</html>