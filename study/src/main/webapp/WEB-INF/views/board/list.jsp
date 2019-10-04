<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>List Page</h1>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>수정일</th>
			</tr>
		</thead>
		<c:forEach items="${list}" var="board">
			<tr>
				<td><c:out value="${board.num}"/>
				<td><c:out value="${board.title}"/>
				<td><c:out value="${board.writer}"/>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.reg_date}"/>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.reg_date}"/>
			</tr>
		</c:forEach>
	</table>
</body>
</html>