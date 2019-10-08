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
<div><h1>Board Read Page</h1></div>
<ul>
	<li><label>Num</label><input id="num" name="num" value='<c:out value="${board.num}"/>' readonly="readonly">
	<li><label>Title</label><input name="title" value='<c:out value="${board.title}"/>' readonly="readonly">
	<li><label>Content</label><input name="content" value='<c:out value="${board.content}"/>' readonly="readonly">
	<li><label>Writer</label><input name="Writer" value='<c:out value="${board.writer}"/>' readonly="readonly">
	<li><button id="update" onclick="location.href='/lookie/board/update?num=${board.num}&page=${page}'">Update</button>
		<button id="list" onclick="location.href='/lookie/board/list?page=${page}'">List</button>
</ul>
	<ul>
		<li><input id="page" value="${page}" hidden="true">
		<li><label>Replyer</label><input id="replyer">
		<li><label>Reply</label><input id="reply">
		<li><button id="new" type="button">Submit</button>
	</ul>
<jsp:include page="../board/replyList.jsp"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#new").click(function(){
			var data={num:$("#num").val(), replyer:$("#replyer").val(), reply:$("#reply").val()}
			$.ajax({
				type:"post",
				url:"/lookie/replies/new",
				data:JSON.stringify(data),
				contentType : "application/json; charset=UTF-8",
				
			});
		});
	});
</script>
</body>
</html>