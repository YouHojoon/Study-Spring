<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/lookie/resources/vendor/jquery/jquery.min.js"></script>
</head>
<body>
	<form action="/lookie/board/upload" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile" multiple="multiple">
		<button type="submit">전송</button>
		<div class="uploadResult">
			<ul>
			<c:set value="0" var="num"/>
			<c:forEach items="${uploadList}" var="upload">
				<c:set value="${num+1}" var="num"/>
				<c:choose>
					<c:when test="${upload.image==true}">
						<input id="uploadPath${num}" value="${upload.uploadPath}" hidden="true">
						<input id="uuid${num}" value="${upload.uuid}" hidden="true">
						<input id="fileName${num}" value="${upload.fileName}" hidden="true">
						<script type="text/javascript">
							var num=${num};
							var path=encodeURIComponent($("#uploadPath"+num).val()+"\\s_"+$("#uuid"+num).val()+"_"+$("#fileName"+num).val());
							$(".uploadResult").append("<li><img src=display?fileName="+path+"></li>");
						</script>
					</c:when>
					<c:otherwise>
						<li>${upload.fileName}</li>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			</ul>
		</div>
	</form>
	
</body>
</html>