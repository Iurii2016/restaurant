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
    <p><a href="/addEmployee">Add employee</a></p>
    <p><a href="/allEmployees">Get all employee</a></p>

    <form action="/getEmployeeByPosition/" method="get">
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

    <form action="/getEmployeeByName/" method="get">
        <p>
            Enter name: <input name="getEmployeeByName" type="text" size="40">
            <input type="submit" class="btn btn-info" value="Get employee by name">
        </p>
    </form>
    <form action="/deleteEmployeeByName/" method="get">
        <p>
            Enter name: <input name="deleteEmployeeByName" type="text" size="40">
            <input type="submit" class="btn btn-danger" value="Delete employee by name">
        </p>
    </form>
</div>
<%--<img src='<c:url value="/img/html.png"/>' height="150" width="150"/>--%>
</body>
</html>
