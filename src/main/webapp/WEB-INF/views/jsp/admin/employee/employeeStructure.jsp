<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Employee operations</title>
</head>
<body>
<div class="container">
    <h2>Select operation:</h2>
    <p><a href="/admin/addEmployee">Add employee</a></p>
    <p><a href="/admin/allEmployees">Get all employee</a></p>

    <form action="/admin/getEmployeeByPosition/" method="get">
        <p>
            Get employees by position:
            <select name="getEmployeeByPosition">
                <c:forEach items="${ListOfPositions}" var="position">
                    <option>${position.name}</option>
                </c:forEach>
            </select>
            <input type="submit" class="btn btn-info" value="Get employee by position">
        </p>
    </form>

    <form action="/admin/getEmployeeByName/" method="get">
        <p>
            Enter name: <input name="getEmployeeByName" type="text" size="40">
            <input type="submit" class="btn btn-info" value="Get employee by name">
        </p>
    </form>
    <form action="/admin/deleteEmployeeByName/" method="get">
        <p>
            Enter name: <input name="deleteEmployeeByName" type="text" size="40">
            <input type="submit" class="btn btn-danger" value="Delete employee by name">
        </p>
    </form>
</div>
</body>
</html>
