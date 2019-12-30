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
          <h1 class="h3 mb-2 text-gray-800">Register</h1>
          <!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Board Register
								Page</h6>
						</div>
						<div class="card-body">
							<form action="/lookie/board/register" method="post">
								<div class="form-group">
									<label for="exampleFormControlInput1">Title</label> <input
										class="form-control" id="exampleFormControlInput1"
										name="title">
								</div>
								<div class="form-group">
									<label for="exampleFormControlTextarea1">Content</label>
									<textarea name="content" class="form-control"
										id="exampleFormControlTextarea1" rows="3"></textarea>
								</div>
								<div class="form-group">
									<label for="exampleFormControlInput1">Writer</label> <input
										class="form-control" id="exampleFormControlInput1"
										name="writer">
								</div>
								<div class="btn-group" role="group" aria-label="Basic example">
									<button class="btn btn-primary" type="submit">Submit</button>
									<button class="btn btn-primary" type="reset">Reset</button>
									<button class="btn btn-primary" type="button"
										onclick="location.href='/lookie/board/list?page=${page}'">List</button>
								</div>
							</form>
						</div>
					</div>
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">File Attach</h6>
						</div>
						<div class="card-body">
							<input class="form-control" type="file" name="uploadFile" multiple="multiple">
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

  <!-- Bootstrap core JavaScript-->
  <script src="/lookie/resources/vendor/jquery/jquery.min.js"></script>
  <script src="/lookie/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="/lookie/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="/lookie/resources/js/sb-admin-2.min.js"></script>
  
</body>

</html>
