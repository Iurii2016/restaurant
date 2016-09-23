<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Dishes</title>
</head>
<body>
<div class="container">
    <h2>Dishes information</h2>
    <table class="table table-striped" style="align-items: center">
        <tr>
            <td>
                <h3>Dish</h3>
                <p>Name: <c:out value="${dish.name}"/></p>
                <p>Category: <c:out value="${dish.categoryId.name}"/></p>
                <p>Price: <c:out value="${dish.price}"/></p>
                <p>Weight: <c:out value="${dish.weight}"/></p>
            </td>
            <td>
                <h3>Ingredients</h3>
                <table class="table table-striped" style="align-items: center">
                    <thead>
                    <tr>
                        <th>Ingredient</th>
                        <th>Quantity</th>
                        <th>Unit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listOfDishIngredients}" var="dishIngredient">
                        <tr>
                            <td>${dishIngredient.ingredientId.ingredient}</td>
                            <td>${dishIngredient.quantity}</td>
                            <td>${dishIngredient.unit}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
