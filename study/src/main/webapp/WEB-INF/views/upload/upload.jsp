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
<style>
	.bigPictureWrapper{
	background-color:gray;
	background: rgba(255,255,255,0.5);
	align-items: center;
	width: 100%;
	height: 100%;
	}
	.bigPicture{
			
		display:flex;
		position: relative;
		align-self: center;
		
	}
</style>
<body>
	<form action="/lookie/upload/upload" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile" multiple="multiple">
		<button type="submit">전송</button>
		<div class="uploadResult">
			<ul>
			<c:set value="0" var="num"/>
			<c:forEach items="${uploadList}" var="upload">
			<c:set value="${num+1}" var="num"></c:set>
				<c:choose>
					<c:when test="${upload.image==true}">
						<li><img onclick="showImage(${num})" src="/lookie/upload/display?fileName=${upload.encodePath}">
						<div class="bigPictureWrapper">
						<img id="${num}" class="bigPicture" onclick="closeImage(${num})" src="/lookie/upload/bigPicture?fileName=${upload.encodePath}">
						</div></li>
					</c:when>
					<c:otherwise>
						<li><a href="/lookie/upload/download?fileName=${upload.encodePath}">${upload.fileName}</a>></li>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			</ul>
		</div>
	</form>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".bigPicture").hide();
		})
		function showImage(img){
				$("#"+img).show().css("display","flex").animate({width:"100%", height: "100%"},1000);
		}
		function closeImage(img){
			$("#"+img).hide().animate({width:"0%", height:"0%"},1000);
		}
		
	</script>
</body>
</html>