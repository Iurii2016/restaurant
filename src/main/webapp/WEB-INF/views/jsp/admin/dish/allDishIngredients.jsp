<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>All dish</title>
</head>
<body>
<div class="container">
    <h2>Dishes information</h2>
    <table class="table table-striped" style="align-items: center">
        <thead>
        <tr>
            <th>Dish</th>
            <th>Ingredient</th>
            <th>Quantity</th>
            <th>Unit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ListOfDishIngredients}" var="dishIngredient">
            <tr>
                <td>${dishIngredient.dishId.name}</td>
                <td>${dishIngredient.ingredientId.ingredient}</td>
                <td>${dishIngredient.quantity}</td>
                <td>${dishIngredient.unit}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

