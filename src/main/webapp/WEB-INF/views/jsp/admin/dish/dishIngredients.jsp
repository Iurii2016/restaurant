<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Dishes</title>
</head>
<body>
<div class="container">
    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h2>Dishes information</h2>
    <%--<button class="btn btn-info" onclick="location.href='/admin/dish/${dish.id}/addIngredient'">Add ingredient</button>--%>
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
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listOfDishIngredients}" var="dishIngredient">
                        <tr>
                            <td>${dishIngredient.ingredientId.ingredient}</td>
                            <td>${dishIngredient.quantity}</td>
                            <td>${dishIngredient.unit}</td>
                            <td>
                                <spring:url value="/admin/dish/${dish.name}/dishIngredient/${dishIngredient.ingredientId.ingredient}/delete"
                                            var="deleteUrl" />
                                <button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </td>
        </tr>
    </table>
    <button class="btn btn-info pull-left" onclick="location.href='/admin/dish/orderBy/id'">Back</button>
    <button class="btn btn-success pull-right" onclick="location.href='/admin/dish/${dish.id}/addIngredient'">Add ingredient</button>
</div>
</body>
</html>
