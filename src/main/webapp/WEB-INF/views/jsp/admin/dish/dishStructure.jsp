<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Dish operations</title>
</head>
<body>
<div class="container">
    <h2>Select operation:</h2>
    <p><a href="/addDish">Add dish</a></p>
    <p><a href="/getAllDishes">Get all dish</a></p>
    <%--<p><a href="/addDishIngredient">Add dish ingredients</a></p>--%>
    <%--<p><a href="/getAllDishIngredients">Get all dish ingredients</a></p>--%>
    <%--<form action="/getDishByName/" method="get">--%>
        <%--<p>--%>
            <%--Enter dish name: <input name="getDishByName" type="text" size="40">--%>
            <%--<input type="submit" class="btn btn-info" value="Get dish by name">--%>
        <%--</p>--%>
    <%--</form>--%>
    <%--<form action="/deleteDishByName/" method="get">--%>
        <%--<p>--%>
            <%--Enter dish name: <input name="deleteDishByName" type="text" size="40">--%>
            <%--<input type="submit" class="btn btn-danger" value="Delete dish by name">--%>
        <%--</p>--%>
    <%--</form>--%>
</div>
</body>
</html>
