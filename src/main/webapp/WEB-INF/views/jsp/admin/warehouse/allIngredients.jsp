<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Ingredients information</title>
</head>
<body>
<div class="container">
    <h2>Ingredients information</h2>
    <table class="table table-striped" style="align-items: center">
        <thead>
        <tr>
            <th>ID</th>
            <th>Ingredient</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listOfIngredients}" var="ingredient">
            <tr>
                <td>${ingredient.id}</td>
                <td>${ingredient.ingredient}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>