<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Employee List</h1>
<a href="${pageContext.request.contextPath}/employees/add">Add Employee</a>
<form method="GET" action="${pageContext.request.contextPath}/employees/search">
    <input type="text" name="name" placeholder="Search by name " />
    <input type="submit" value="Search" />
</form>
<table border="1">
    <tr>
        <th>Employee ID</th>
        <th>Employee Name</th>
        <th>Employee email</th>
        <th>Employee phone number</th>
        <th>Employee avt</th>
        <th>Action</th>
    </tr>
    <c:forEach var="employee" items="${employee}">
        <tr>
            <td>${employee.employeeId}</td>
            <td>${employee.fullName}</td>
            <td>${employee.email}</td>
            <td>${employee.phoneNumber}</td>
            <td><img src="${employee.avtUrl}" alt="Avatar" style="width:50px;height:50px;"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/employees/edit/${employee.employeeId}">Edit</a>
                <a href="${pageContext.request.contextPath}/employees/delete/${employee.employeeId}">Delete</a>
        </tr>
    </c:forEach>

</body>
</html>
