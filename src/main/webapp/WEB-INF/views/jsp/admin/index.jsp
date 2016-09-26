<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="fragments/header.jsp"/>
    <title>Restaurant</title>
</head>
<body>
<div class="container">
    <h1>DB: Restaurant</h1>
    <br>
    <p>Select table:</p>

    <p><a href="/allEmployees">Employees</a></p>
    <p><a href="/getAllDishes">Dishes</a></p>
    <p><a href="/menuStructure">Menu</a></p>
    <p><a href="/ordersStructure">Orders</a></p>
    <p><a href="/getCookedDishes">Kitchen</a></p>
    <p><a href="/warehouseStructure">Warehouse</a></p>
</div>
</body>
</html>