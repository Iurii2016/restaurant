<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Warehouse operations</title>
</head>
<body>
<div class="container">
    <h2>Select operation:</h2>
    <%--<p><a href="/addIngredient">Add new ingredient into the reference book</a></p>--%>
    <%--<p><a href="/getIngredients">Get all ingredients</a></p>--%>
    <p><a href="/admin/addWarehouse">Add ingredient into warehouse</a></p>
    <p><a href="/admin/getWarehouseBalance">Warehouse balance</a></p>

    <%--<form action="/getEndingIngredients/" method="get">--%>
        <%--<p>--%>
            <%--Enter a number: <input name="getEndingIngredients" type="text" size="40">--%>
            <%--<input type="submit" class="btn btn-info" value="Get ending ingredient">--%>
        <%--</p>--%>
    <%--</form>--%>

    <%--<form action="/getBalanceByName/" method="get">--%>
        <%--<p>--%>
            <%--Enter ingredient's name: <input name="getBalanceByName" type="text" size="40">--%>
            <%--<input type="submit" class="btn btn-info" value="Get balance by ingredient">--%>
        <%--</p>--%>
    <%--</form>--%>

    <%--<form action="/changeIngredientQuantity/" method="get">--%>
        <%--<p>--%>
            <%--Enter ingredient's name: <input name="ingredientName" type="text" size="40">--%>
            <%--Enter new quantity: <input name="newQuantity" type="text" size="10">--%>
            <%--<input type="submit" class="btn btn-info" value="Change quantity">--%>
        <%--</p>--%>
    <%--</form>--%>

    <%--<form action="/deleteIngredientFromWarehouse/" method="get">--%>
        <%--<p>--%>
            <%--Enter ingredient's name: <input name="deleteIngredient" type="text" size="40">--%>
            <%--<input type="submit" class="btn btn-danger" value="Delete ingredient from warehouse">--%>
        <%--</p>--%>
    <%--</form>--%>
</div>
</body>
</html>
