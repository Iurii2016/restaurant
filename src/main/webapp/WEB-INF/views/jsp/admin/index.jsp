<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="fragments/header.jsp"/>
    <title>Restaurant</title>
</head>
<body>
<div class="container">
    <div class="container">
        <h1>Grill'age</h1>
        <div class="list-group">
            <a href="/admin/allEmployees" class="list-group-item">Employees</a>
            <a href="/admin/getAllDishes" class="list-group-item">Dishes</a>
            <a href="/admin/getAllMenu" class="list-group-item">Menu</a>
            <a href="/admin/getAllOrders" class="list-group-item">Orders</a>
            <a href="/admin/getCookedDishes" class="list-group-item">Kitchen</a>
            <a href="/admin/getWarehouseBalance" class="list-group-item">Warehouse</a>
        </div>
    </div>
</div>
</body>
</html>