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
    <%--<script>--%>
        <%--function sort(id) {--%>
            <%--var element = document.getElementById(id);--%>
            <%--if (element.className=="fa fa-caret-down"){--%>
                <%--element.className="fa fa-caret-up";--%>
            <%--}else {element.className="fa fa-caret-down"}--%>
        <%--}--%>
    <%--</script>--%>
</head>
<body>
<div class="container">
    <h2>Orders information <c:if test="${position=='waiter'}">of ${waiter.name} ${waiter.surname}</c:if></h2></h2>
    <table id="myTable" class="table table-striped" style="align-items: center">
        <thead>
        <tr>
            <%--<th>ID <button onclick="sort('idPicture')"><i id="idPicture" class="fa fa-caret-down"/></th>--%>
            <th>ID <button onclick="location.href='/admin/order/orderBy/${"id"}'"><i class="fa fa-caret-down"/></th>
            <th>Waiter <button onclick="location.href='/admin/order/orderBy/${"employeeId"}'"><i class="fa fa-caret-down"/></th>
            <th>Table number <button onclick="location.href='/admin/order/orderBy/${"tableNumber"}'"><i class="fa fa-caret-down"/></th>
            <th>Date <button onclick="location.href='/admin/order/orderBy/${"date"}'"><i class="fa fa-caret-down"/></th>
            <th>Status <button onclick="location.href='/admin/order/orderBy/${"status"}'"><i class="fa fa-caret-down"/></th>
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
            <c:forEach items="${ListOfOrders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.employeeId.name}</td>
                    <td>${order.tableNumber}</td>
                    <td>${order.date}</td>
                    <td>${order.status}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
