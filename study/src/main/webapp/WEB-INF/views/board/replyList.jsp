<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>댓글</h1>
	<c:if test="${reply==null}">
		<h1>댓글</h1>
	</c:if>
	<c:forEach var="list" items="${reply}">
		<ul>
			<li><c:out value="${list.replyer}"/><fmt:formatDate pattern="yyyy-MM-dd" value="${list.update_date}"/>
			<li><c:out value="${list.reply}"/>
		</ul>
	</c:forEach>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
	
			$.ajax({
				type:"get",
				url:"/lookie/replies/list?num="+$("#num").val()+"&page="+${"#page"}.val()
				
			});
	
</script>
</body>
</html>