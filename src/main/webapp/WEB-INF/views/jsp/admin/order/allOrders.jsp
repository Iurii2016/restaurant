<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Orders</title>
    <style>
        * {
            box-sizing: border-box;
        }

        #inputWaiter, #inputStatus, #inputData, #inputTable, #inputID {
            background-image: url('/img/search-icon.png');
            background-position: 10px 10px;
            background-repeat: no-repeat;
            width: 100%;
            padding: 8px 0px 8px 40px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Orders information <c:if test="${position=='waiter'}">of ${waiter.name} ${waiter.surname}</c:if></h2></h2>
    <table id="myTable" class="table table-striped" style="align-items: center">
        <thead>
        <tr>
            <th>ID</th>
            <c:choose>
                <c:when test="${position=='waiter'}"/>
                <c:otherwise>
                    <th>Waiter</th>
                </c:otherwise>
            </c:choose>
            <th>Table number</th>
            <th>Date</th>
            <th>Status</th>
            <th></th>
            <th></th>
        </tr>
        <tr>
            <th><input type="text" id="inputID" onkeyup="myFunction('inputID', 0)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputWaiter" onkeyup="myFunction('inputWaiter', 1)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputTable" onkeyup="myFunction('inputTable', 2)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputData" onkeyup="myFunction('inputData', 3)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputStatus" onkeyup="myFunction('inputStatus', 4)" placeholder="Search.." title="Type in a name"></th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${position=='waiter'}">
                <c:forEach items="${waiter.orders}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.tableNumber}</td>
                        <td>${order.date}</td>
                        <td>${order.status}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach items="${ListOfOrders}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.employeeId.name}</td>
                        <td>${order.tableNumber}</td>
                        <td>${order.date}</td>
                        <td>${order.status}</td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>
