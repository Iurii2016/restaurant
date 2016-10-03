<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Order information</title>
</head>
<body>
<div class="container">
    <h2>Order information</h2>
    <table class="table table-striped" style="align-items: center">
        <tr>
            <td>
                <h3>Order</h3>
                <p>Number: <c:out value="${order.id}"/></p>
                <p>Date: <c:out value="${order.date}"/></p>
                <p>Waiter: <c:out value="${order.employeeId.name}"/></p>
                <p>Table: <c:out value="${order.tableNumber}"/></p>
                <p>Status: <c:out value="${order.status}"/></p>
            </td>
            <td>
                <h3>Dishes</h3>
                <table class="table table-striped" style="align-items: center">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Weight</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listOfDishes}" var="dish">
                            <tr>
                                <td>${dish.name}</td>
                                <td>${dish.price}</td>
                                <td>${dish.weight}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </td>
        </tr>
    </table>
    <button class="btn btn-info" onclick="location.href='/admin/getAllOrders'">Back</button>
</div>
</body>
</html>
