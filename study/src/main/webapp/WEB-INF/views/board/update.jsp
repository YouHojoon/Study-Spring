<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div><h1>Board Update Page</h1></div>
<form action="/lookie/board/update?page=${page}" method="post">
	<ul>
		<li><label>Num</label><input name="num" value='<c:out value="${board.num}"/>' readonly="readonly">
		<li><label>Title</label><input name="title" value='<c:out value="${board.title}"/>'>
		<li><label>Content</label><input name="content" value='<c:out value="${board.content}"/>'>
		<li><label>Writer</label><input name="Writer" value='<c:out value="${board.writer}"/>'>
		<li><button id="update" type="submit">Update</button>
		<button id="list" onclick="location.href='/lookie/board/list?page=${page}'">List</button>
		<button id="delete" type="button">Delete</button>
	</ul>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#delete").click(function(){
			var check=confirm("정말 삭제하시겠습니까?");
			if(check)
				location.href="/lookie/board/delete?num=${board.num}&page=${page}";
			else
				return;
		});
	});
</script>
</body>
</html>