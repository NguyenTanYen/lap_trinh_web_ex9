<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<!-- FontAwesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />

<title>List Categories</title>
</head>
<body>

	<div class="container mt-4">
		<div class="card">
			<div class="card-header">List Categories</div>

			<div class="card-body">
				<!-- Hiển thị thông báo -->
				<c:if test="${not empty message}">
					<div class="alert alert-primary" role="alert">${message}</div>
				</c:if>

				<table class="table table-striped table-bordered table-responsive">
					<thead class="table-dark text-center">
						<tr>
							<th>Category ID</th>
							<th>Category Code</th>
							<th>Category Name</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${categories}" var="category">
							<tr>
								<td>${category.categoryId}</td>
								<td>${category.categoryCode}</td>
								<td>${category.categoryName}</td>
								<td><c:choose>
										<c:when test="${category.status}">
											<span class="badge bg-success">Active</span>
										</c:when>
										<c:otherwise>
											<span class="badge bg-secondary">Inactive</span>
										</c:otherwise>
									</c:choose></td>
								<td class="text-center"><a
									href="/admin/categories/view/${category.categoryId}"
									class="btn btn-sm btn-info"> <i class="fa fa-info"></i>
								</a> <a href="/admin/categories/edit/${category.categoryId}"
									class="btn btn-sm btn-warning"> <i class="fa fa-edit"></i>
								</a> <a href="/admin/categories/delete/${category.categoryId}"
									class="btn btn-sm btn-danger"
									onclick="return confirm('Bạn có chắc chắn muốn xóa?');"> <i
										class="fa fa-trash"></i>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>
