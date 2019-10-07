<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
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
		<c:if test="${serchList==null}">
			<c:forEach items="${list}" var="board">
				<tr>
					<td><c:out value="${board.rowNum+(pageMaker.page-1)*10}"/>
					<td><a href='/lookie/board/select?num=${board.num}&page=${pageMaker.page}'>
					<c:out value="${board.title}"/></a></td>
					<td><c:out value="${board.writer}"/>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.reg_date}"/>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.update_date}"/>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${serchList!=null}">
			<c:forEach items="${serchList}" var="board">
				<tr>
					<td><c:out value="${board.rowNum+(pageMaker.page-1)*10}"/>
					<td><a href='/lookie/board/select?num=${board.num}&page=${pageMaker.page}'>
					<c:out value="${board.title}"/></a></td>
					<td><c:out value="${board.writer}"/>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.reg_date}"/>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.update_date}"/>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<c:if test="${pageMaker.prev}">
		<a href="list?page=${pageMaker.startPage-1}">Previous</a>
	</c:if>
	<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
		<a href="list?page=${num}">${num}</a>
	</c:forEach>
	<c:if test="${pageMaker.next}">
		<a href="list?page=${pageMaker.endPage+1}">Next</a>
	</c:if>	
	<div>
		<button id="register" type="button" 
		onclick="location.href='/lookie/board/register?page=${pageMaker.page}'">Register</button>
	</div>
	<form action="/lookie/board/list?page=${pageMaker.page}" method="post">
		<select name="type">
			<option value="" <c:out value="${type==''?'selected':''}"/>></option>
			<option value="T" <c:out value="${type=='T'?'selected':''}"/>>제목</option>
			<option value="W" <c:out value="${type=='W'?'selected':''}"/>>글쓴이</option>
			<option value="C" <c:out value="${type=='C'?'selected':''}"/>>내용</option>
		</select>
		<input type="text" name="keyword" value="${keyword}"/>
		<input type="hidden" name="page" value="${pageMaker.page}">
		<button type="submit">검색</button>
	</form>
</body>
</html>