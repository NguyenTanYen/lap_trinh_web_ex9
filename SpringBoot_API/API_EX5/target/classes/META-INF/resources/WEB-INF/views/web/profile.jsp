<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-4">
    <h2>Profile</h2>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/profile/update" 
          method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label class="form-label">Full name</label>
            <input type="text" name="fullName" class="form-control" 
                   value="${user.fullName}" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Phone</label>
            <input type="text" name="phone" class="form-control" 
                   value="${user.phone}">
        </div>
        <div class="mb-3">
            <label class="form-label">Upload Image</label>
            <input type="file" name="file" class="form-control">
        </div>
        <c:if test="${not empty user.images}">
            <div class="mb-3">
                <img src="${pageContext.request.contextPath}/uploads/${user.images}" 
                     alt="avatar" style="max-height:150px;">
            </div>
        </c:if>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
</body>
</html>
