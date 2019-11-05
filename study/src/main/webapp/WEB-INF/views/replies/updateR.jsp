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
		<li><input id="num" value="${replySelect.num }" hidden="true">
		<li><input id="page" value="${page}" hidden="true">
		<li><input id="rnum" value="${replySelect.rnum }" hidden="true">
		<li><input id="replyer" value="${replySelect.replyer}">
		<li><input id="reply" value="${replySelect.reply}"><button id="update">Update</button>
		<button id="list" onclick="location.href='/lookie/board/select?num=${replySelect.num}&page=${page}&rpage=1'">List</button>
	</ul>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#update").click(function(){
			var data={rnum:$("#rnum").val(), replyer:$("#replyer").val(), reply:$("#reply").val()};
			$.ajax({
				type:"post",
				url:"/lookie/replies/updateR",
				data:JSON.stringify(data),
				contentType : "application/json; charset=UTF-8",
				success : function(){
					location.href="/lookie/board/select?num="+$("#num").val()+"&page=" +$("#page").val()+"&rpage=1";
				}
			});
		});
	});
</script>
</html>