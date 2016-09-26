<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Order operations</title>
</head>
<body>
<div class="container">
    <h2>Select operation:</h2>
    <p><a href="/admin/addOrder">Add new order</a></p>
    <p><a href="/admin/getAllOrders">All orders</a></p>
    <p><a href="/admin/getOpenedOrders">Get opened orders</a></p>
    <p><a href="/admin/getClosedOrders">Get closed orders</a></p>
    <form action="/admin/setClosedStatus/" method="get">
        <p>
            Enter order ID: <input name="setClosedStatus" type="text" size="40">
            <input type="submit" class="btn btn-info" value="Set closed status">
        </p>
    </form>
    <form action="/admin/deleteOpenedOrder/" method="get">
        <p>
            Enter order ID: <input name="deleteOpenedOrder" type="text" size="40">
            <input type="submit" class="btn btn-danger" value="Delete opened order">
        </p>
    </form>
</div>
</body>
</html>
