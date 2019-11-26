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
          <h1 class="h3 mb-2 text-gray-800">LIST</h1>
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Board List Page</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
              	<div class="dataTables_wrapper dt-bootstrap4" id="dataTable_wrapper">
              		<div class="row">
              			<div class="col-sm-12">
              				<form method="post" action="/lookie/board/list">
  								<div class="form-row align-items-center" style="float:right">
    								<div class="col-auto my-1">
      									<label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Preference</label>
      										<select name="type" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
        										<option value="" <c:out value="${type==''?'selected':''}"/>>-----------</option>
												<option value="T" <c:out value="${type=='T'?'selected':''}"/>>제목</option>
												<option value="W" <c:out value="${type=='W'?'selected':''}"/>>글쓴이</option>
												<option value="C" <c:out value="${type=='C'?'selected':''}"/>>내용</option>
      										</select>
    								</div>
    								<div class="col-auto my-1">
      									<div class="input-group">
              								<input name="keyword" class="form-control bg-light border-0 small" aria-describedby="basic-addon2" aria-label="Search" type="text" placeholder="Search for..." value="${keyword }">
              									<div class="input-group-append">
                									<button class="btn btn-primary" type="submit">
                  										<i class="fas fa-search fa-sm"></i>
                									</button>
              									</div>
            							</div>
    								</div>
  								</div>
							</form>
              			</div>
              		</div>
                	<div class="row">
                		<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  			<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
									<th>수정일</th>
								</tr>
							</thead>
							<c:forEach items="${serchList}" var="board">
								<tr>
									<td><c:out value="${board.rowNum+(pageMaker.page-1)*10}"/>
									<td><a href='/lookie/board/select?num=${board.num}&page=${pageMaker.page}&rpage=1'>
									<c:out value="${board.title}"/></a><b>[<c:out value="${board.replyCnt}"/>]</b></td>
									<td><c:out value="${board.writer}"/>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.reg_date}"/>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.update_date}"/>
								</tr>
							</c:forEach>
              			</table>
              		</div>
              		<div class="row">
              			<div class="col-sm-12 col-md-5">
              				<button class="btn btn-primary" type="button" onclick="location.href='/lookie/board/register?page=${pageMaker.page}'">Register</button>
              			</div>
              			<div class="col-sm-12 col-md-7">
              				<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
              					<ul class="pagination">
              						<c:if test="${pageMaker.prev}">
										<li class="paginate_button page-item previous"><a tabindex="0" class="page-link" aria-controls="dataTable" href="list?page=${pageMaker.startPage-1}">Previous</a></li>
									</c:if>
									<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
										<li id="${num}"class="paginate_button page-item" ><a tabindex="0" class="page-link" aria-controls="dataTable" href="list?page=${num}">${num}</a></li>
									</c:forEach>
									<c:if test="${pageMaker.next}">
										<li class="paginate_button page-item next"><a tabindex="0" class="page-link" aria-controls="dataTable" href="list?page=${pageMaker.endPage+1}">Next</a></li>
									</c:if>	
              					</ul>
              				</div>
						</div>
					</div>            
              	</div>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

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

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>
  <!-- Custom Modal-->
  <div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Board Register</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body"></div>
        <div class="modal-footer">
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
  <input id="page" value="${pageMaker.page}" hidden="true">
  <script>
  	$(document).ready(function(){
  		var page=$("#page").val();
  		var result='<c:out value="${result}"/>';
  		document.getElementById(page).className="paginate_button page-item active";
  		registerModal(result);
  		function registerModal(result){
  			if(result==""){
  				return;
  			}
  			if(parseInt(result)>0){
  				$(".modal-body").html("게시글이 등록되었습니다.");
  				$("#registerModal").modal("show");
  			}
  			if(result=="update"){
  				$(".modal-body").html("게시글이 수정되었습니다.");
				$("#registerModal").modal("show");
				}
  			if(result=="delete"){
  				$(".modal-body").html("게시글이 삭제되었습니다.");
				$("#registerModal").modal("show");
				}
  		}
  	});
  </script>
</body>

</html>
