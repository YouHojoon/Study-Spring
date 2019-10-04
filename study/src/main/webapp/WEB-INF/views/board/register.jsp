<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Board Register</h1>
	<form  action="/lookie/board/register" method="post">
		<ul>
			<li><label>Title</label><input name="title">
			<li><label>Text area</label><textarea rows="3" name="content">
			</textarea>
			<li><label>Writer</label><input name="writer">
			<li><button type="submit">Submit</button><button type="reset">Reset</button>
		</ul>
	</form>
</body>
</html>