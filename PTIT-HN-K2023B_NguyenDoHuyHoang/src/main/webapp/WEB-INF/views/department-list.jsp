<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Department List</h1>
<a href="${pageContext.request.contextPath}/departments/add">Add Department</a>
<form method="GET" action="${pageContext.request.contextPath}/departments/search">
    <input type="text" name="name" placeholder="Search by name " />
    <input type="submit" value="Search" />
</form>
<table border="1">
    <tr>
        <th>Department ID</th>
        <th>Department Name</th>
        <th>Department Description</th>
        <th>Department Status</th>
        <th>Action</th>
    </tr>
    <c:forEach var="department" items="${department}">
        <tr>
            <td>${department.departmentId}</td>
            <td>${department.departmentName}</td>
            <td>${department.decscription}</td>
            <td>${department.status}</td>
            <td>
                <a href="${pageContext.request.contextPath}/departments/edit/${department.departmentId}">Edit</a>
                <a href="${pageContext.request.contextPath}/departments/delete/${department.departmentId}">Delete</a>
        </tr>
    </c:forEach>

</body>
</html>
