<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<title><c:choose>
		<c:when test="${category.categoryId != null}">Edit Category</c:when>
		<c:otherwise>Add Category</c:otherwise>
	</c:choose></title>
</head>
<body>

	<div class="container mt-4">
		<div class="card">
			<div class="card-header">
				<c:choose>
					<c:when test="${category.categoryId != null}">
                    Edit Category
                </c:when>
					<c:otherwise>
                    Add New Category
                </c:otherwise>
				</c:choose>
			</div>

			<div class="card-body">
				<!-- Hiển thị thông báo -->
				<c:if test="${not empty message}">
					<div class="alert alert-info">${message}</div>
				</c:if>

				<!-- Form -->
				<form:form modelAttribute="category" method="post"
					action="${pageContext.request.contextPath}/admin/categories/saveOrUpdate"
					cssClass="row g-3">

					<!-- Hidden field cho ID -->
					<form:hidden path="categoryId" />

					<div class="col-md-6">
						<label for="categoryCode" class="form-label">Category Code</label>
						<form:input path="categoryCode" cssClass="form-control"
							id="categoryCode" />
						<form:errors path="categoryCode" cssClass="text-danger" />
					</div>

					<div class="col-md-6">
						<label for="categoryName" class="form-label">Category Name</label>
						<form:input path="categoryName" cssClass="form-control"
							id="categoryName" />
						<form:errors path="categoryName" cssClass="text-danger" />
					</div>

					<div class="col-md-6">
						<label for="images" class="form-label">Image URL</label>
						<form:input path="images" cssClass="form-control" id="images" />
					</div>

					<div class="col-md-6">
						<label for="status" class="form-label">Status</label>
						<form:select path="status" cssClass="form-select" id="status">
							<form:option value="true" label="Active" />
							<form:option value="false" label="Inactive" />
						</form:select>
					</div>

					<div class="col-12 text-end">
						<button type="submit" class="btn btn-success">
							<i class="fa fa-save"></i> Save
						</button>
						<a href="${pageContext.request.contextPath}/admin/categories/list"
							class="btn btn-secondary"> <i class="fa fa-arrow-left"></i>
							Back
						</a>
					</div>
				</form:form>
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
