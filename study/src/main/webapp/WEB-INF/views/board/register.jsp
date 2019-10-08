<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Board Register</h1>
	<form  action="/lookie/board/register?page=${page}" method="post">
		<ul>
			<li><label>Title</label><input name="title">
			<li><label>Text area</label><textarea rows="3" name="content">
			</textarea>
			<li><label>Writer</label><input name="writer">
			<li><button type="submit">Submit</button><button type="reset">Reset</button>
			<button type="button" onclick="location.href='/lookie/board/list?page=${page}'">List</button>
		</ul>
	</form>
	
</body>
</html>