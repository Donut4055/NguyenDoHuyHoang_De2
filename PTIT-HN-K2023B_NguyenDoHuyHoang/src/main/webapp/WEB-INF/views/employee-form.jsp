<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add Employee</h1>

<form method="POST">
    <table>
        <tr>
            <td>Employee Full Name:</td>
            <td><input type="text" name="fullName" value="${employee.fullName}" required/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="email" name="email" value="${employee.email}" /></td>
        </tr>
        <tr>
            <td>Phone Number:</td>
            <td><input type="text" name="phoneNumber" value="${employee.phoneNumber}" required/></td>
        </tr>
        <tr>
            <td>Avatar:</td>
            <td><input type="text" name="avtUrl" value="${employee.avtUrl}" /></td>
        </tr>
        <tr>
            <td>Department:</td>
            <td>
                <input type="number" name="departmentId" value="${employee.departmentId}"/>
            </td>
        <tr>
            <td colspan="2">
                <input type="hidden" name="employeeId" value="${employee.employeeId}"/>
                <input type="submit" value="Save"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
