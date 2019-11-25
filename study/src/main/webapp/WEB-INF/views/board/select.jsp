<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>List</title>

  <!-- Custom fonts for this template -->
  <link href="/lookie/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="/lookie/resources/css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="/lookie/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">
  	<%@include file="../includes/sidebar.jsp"%>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">
      	<%@include file="../includes/topbar.jsp" %>
        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">${board.title}</h1>
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Board Read Page</h6>
            </div>
            <div class="card-body">
            	<div class="form-group">
   	 				<label for="exampleFormControlInput1">Num</label>
    				<input class="form-control" id="exampleFormControlInput1" name="num" value='<c:out value="${board.num}"/>' readonly="readonly">
  				</div>
            	<div class="form-group">
   	 				<label for="exampleFormControlInput1">Title</label>
    				<input class="form-control" id="exampleFormControlInput1" name="title" value='<c:out value="${board.title}"/>' readonly="readonly">
  				</div>
            	<div class="form-group">
    				<label for="exampleFormControlTextarea1">Content</label>
    				<input class="form-control" id="exampleFormControlInput1" name="content" style="height:300px" value='<c:out value="${board.content}"/>' readonly="readonly">
  				</div>
  				<div class="form-group">
   	 				<label for="exampleFormControlInput1">Writer</label>
    				<input class="form-control" id="exampleFormControlInput1" name="writer" value='<c:out value="${board.writer}"/>' readonly="readonly">
  				</div>
  				<div class="form-group">
   	 					<label for="exampleFormControlInput1">Reg_Date</label>
    					<input class="form-control" id="exampleFormControlInput1" name="reg_date" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${board.reg_date}"/>' readonly="readonly">
  				</div>
  				<div class="btn-group" role="group" aria-label="Basic example">
  					<button class="btn btn-primary" type="button" id="update" onclick="location.href='/lookie/board/update?num=${board.num}&page=${page}'">Update</button>
            		<button class="btn btn-primary" type="button" onclick="location.href='/lookie/board/list?page=${page}'">List</button>
				</div>
				<input id="page" value="${page}" hidden="true">
            	<div class="form-group">
   	 				<label for="exampleFormControlInput1">Replyer</label>
    				<input class="form-control" id="replyer">
  				</div>
            	<div class="form-group">
    				<label for="exampleFormControlTextarea1">Reply</label>
    				<textarea class="form-control" id="reply" rows="3"></textarea>
  				</div>
  				<button class="btn btn-primary" type="button" id="new">Submit</button>
          	</div>
         </div>
        </div>
        <!-- /.container-fluid -->
		<div class="container-fluid">
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Reply</h6>
            </div>
            <div class="card-body">
            	<div class="form-group">
            		<c:set var="now" value="<%=new java.util.Date().getTime()%>"/>
            			<c:forEach var="list" items="${reply}">
            				<div class="row">
            					<div class="col-sm-12 col-md-5" style="padding-top:0.5rem">
            						<strong><label for="reply"><c:out value="${list.replyer}"/></label></strong>
            					</div>
            					<div class="col-sm-12 col-md-7" style="text-align:right">
									<c:choose>
										<c:when test="${now-list.update_date.getTime()<(1000*60*60*24)}">
											<fmt:formatDate pattern="HH:mm:ss" value="${list.update_date}"/>
										</c:when>
										<c:otherwise>
											<fmt:formatDate pattern="yyyy-MM-dd" value="${list.update_date}"/>
										</c:otherwise>
									</c:choose>
								</div>
            				</div>
							<p>${list.reply}</p>
							<input id="num" value="${list.num}" hidden="true">
							<button id="update" class="btn btn-primary btn-sm" onclick="update(${list.rnum})">Update</button>
						</c:forEach>
  				</div>
  				<div class="col-sm-12">
              		<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate" style="float:right">
              			<ul class="pagination">
              				<c:if test="${rpageMaker.prev}">
								<li class="paginate_button page-item previous"><a tabindex="0" class="page-link" aria-controls="dataTable" href="select?num=${board.num}&page=${page}&rpage=${rpageMaker.startPage-1}">Previous</a></li>
							</c:if>
							<c:forEach var="rnum" begin="${rpageMaker.startPage}" end="${rpageMaker.endPage}">
								<li id="${num}" class="paginate_button page-item" ><a tabindex="0" class="page-link" aria-controls="dataTable" href="select?num=${board.num}&page=${page}&rpage=${rnum}">${rnum}</a></li>
							</c:forEach>
							<c:if test="${rpageMaker.next}">
								<li class="paginate_button page-item next"><a tabindex="0" class="page-link" aria-controls="dataTable" href="select?num=${board.num}&page=${page}&rpage=${rpageMaker.endPage+1}">Next</a></li>
							</c:if>	
              			</ul>
              		</div>
				</div>
          	</div>
         </div>
        </div>
      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; YouHoJoon of Lookie on 2019</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
  <!--Custom modal-->>
	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    	<div class="modal-dialog" role="document">
      		<div class="modal-content">
        		<div class="modal-header">
          			<h5 class="modal-title" id="exampleModalLabel">Board Register</h5>
         	 		<button class="close" type="button" data-dismiss="modal" aria-label="Close" onclick="location.replace='/lookie/board/select?num=${board.num}&page=${page}&rpage=1'">
            			<span aria-hidden="true">×</span>
          			</button>
        		</div>
        		<div class="modal-body" id="register-modal-body">댓글이 등록되었습니다.</div>
        		<div class="modal-footer"></div>
      		</div>
    	</div>
  	</div>
  	<!--Reply modal-->>
	<div class="modal fade" id="replyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    	<div class="modal-dialog" role="document">
      		<div class="modal-content">
        		<div class="modal-header">
          			<h5 class="modal-title" id="exampleModalLabel">Update Reply</h5>
         	 		<button class="close" type="button" data-dismiss="modal" aria-label="Close">
            			<span aria-hidden="true">×</span>
          			</button>
        		</div>
        		<div class="modal-body">
            		<div class="form-group">
   	 					<label for="exampleFormControlInput1">Num</label>
    					<input class="form-control" id="modalRnum" readonly="readonly">
  					</div>
            		<div class="form-group">
   	 					<label for="exampleFormControlInput1">Replyer</label>
    					<input class="form-control" id="modalReplyer">
  					</div>
            		<div class="form-group">
    					<label for="exampleFormControlTextarea1">Reply</label>
    					<input class="form-control" id="modalReply">
  					</div>
  					<div class="form-group">
    					<label for="exampleFormControlTextarea1">Reg_Date</label>
    					<input class="form-control" id="modalReg_date" readonly="readonly">
  					</div>
        		</div>
        		<div align="center" style="padding-bottom: 2rem">
        			<div class="btn-group" role="group" aria-label="Basic example">
  						<button class="btn btn-primary" type="button" id="updateR">Update</button>
            			<button class="btn btn-primary" type="button" id="deleteR">Delete</button>
					</div>
        		</div>
      		</div>
    	</div>
  	</div>
  <!-- Bootstrap core JavaScript-->
  <script src="/lookie/resources/vendor/jquery/jquery.min.js"></script>
  <script src="/lookie/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="/lookie/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="/lookie/resources/js/sb-admin-2.min.js"></script>
  <script type="text/javascript">
	$(document).ready(function(){
		$("#new").click(function(){
				var reply={num:$("#num").val(),reply:$("#reply").val(),replyer:$("#replyer").val()};
				$.ajax({
					type:"post",
					url:"/lookie/replies/new?page="+$("#page").val(),
					data:JSON.stringify(reply),
					contentType:"application/json; charset=UTF-8",
					success:function(){
						$("#registerModal").modal("show");
					}
				});
			});
		$("#updateR").click(function(){
			var update={rnum:$("#modalRnum").val(),reply:$("#modalReply").val(),replyer:$("#modalReplyer").val()};
			$.ajax({
				type:"post",
				url:"/lookie/replies/updateR",
				data:JSON.stringify(update),
				contentType:"application/json; charset=UTF-8",
				success:function(){
					$("#replyModal").modal("hide");
					$("#register-modal-body").html("수정되었습니다.");
					$("#registerModal").modal("show");
				}
			});
		});
		$("#deleteR").click(function(){
			var check=confirm("정말 삭제하시겠습니까?");
			if(check){
				$.ajax({
					type:"delete",
					url:"/lookie/replies/deleteR?rnum="+$("#modalRnum").val(),
					success:function(){
						$("#replyModal").modal("hide");
						$("#register-modal-body").html("삭제되었습니다.");
						$("#registerModal").modal("show");
					}
				});
			}
			else
				return;
			
		});
	});
	function update(num){
		$.get("/lookie/replies/selectR?rnum="+num,function(data){
			$("#modalRnum").val(data.rnum);
			$("#modalReplyer").val(data.replyer);
			$("#modalReply").val(data.reply);
			var date=new Date(data.reg_date);
			var y=date.getFullYear();
			var m=date.getMonth()+1;
			var d=date.getDate();
			$("#modalReg_date").val(y+'-'+m+'-'+d);
		});
		$("#replyModal").modal("show");
	}
	</script>
	
</body>

</html>
