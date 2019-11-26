<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/lookie/board/upload" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile" multiple="multiple">
		<button type="submit">전송</button>
		<c:forEach items="${uploadList}" var="upload">
			<ul>
				<c:choose>
					<c:when test="${upload.image==true}">
						<li><img src="/lookie/board/display?fileName=${upload.uploadPath}\s_${upload.uuid}_${upload.fileName}">${upload.fileName}</li>
					</c:when>
				
					<c:otherwise>
						<li>${upload.fileName}</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</c:forEach>
	</form>
</body>
</html>