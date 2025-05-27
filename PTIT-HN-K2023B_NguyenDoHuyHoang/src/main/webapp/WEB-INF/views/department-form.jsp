<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add department</h1>

<form method="POST">
    <table>
        <tr>
            <td>Department Name:</td>
            <td><input type="text" name="departmentName" value="${department.departmentName}" required/></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><input type="text" name="decscription" value="${department.decscription}" /></td>
        </tr>
        <tr>
            <td>Status:</td>
            <td>
                <input type="radio" name="status" value="1" ${department.status == 1 ? 'checked' : ''}/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="hidden" name="departmentId" value="${department.departmentId}"/>
                <input type="submit" value="Save"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
