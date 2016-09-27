<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="fragments/header.jsp"/>
    <title>Restaurant</title>
</head>
<body>
<div class="container">
    <h1>Grill'age</h1>
    <br>
    <p>Select table:</p>

    <p><a href="/admin/allEmployees">Employees</a></p>
    <p><a href="/admin/getAllDishes">Dishes</a></p>
    <p><a href="/admin/getAllMenu">Menu</a></p>
    <p><a href="/admin/ordersStructure">Orders</a></p>
    <p><a href="/admin/getCookedDishes">Kitchen</a></p>
    <p><a href="/admin/getWarehouseBalance">Warehouse</a></p>
</div>
</body>
</html>