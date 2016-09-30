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

        #inputID, #inputWaiter, #inputTable, #inputData, #inputStatus {
            background-image: url('/img/search-icon.png');
            background-position: 10px 10px;
            background-repeat: no-repeat;
            width: 100%;
            padding: 8px 0px 8px 40px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
        #newOrder {
            width: 100%;
            padding: 8px 0px 8px 0px;
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
    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>
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
            <th colspan="2">Action</th>
        </tr>
        <tr>
            <th><input type="text" id="inputID" onkeyup="myFunction('inputID', 0)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputWaiter" onkeyup="myFunction('inputWaiter', 1)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputTable" onkeyup="myFunction('inputTable', 2)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputData" onkeyup="myFunction('inputData', 3)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputStatus" onkeyup="myFunction('inputStatus', 4)" placeholder="Search.." title="Type in a name"></th>
            <th colspan="2"><button id="newOrder"class="btn btn-success" onclick="location.href='/admin/addOrder'">Add new order</button></th>
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
                    <td>
                        <spring:url value="/admin/order/${order.id}/closed" var="closedUrl" />
                        <button class="btn btn-info" onclick="location.href='${closedUrl}'">Close</button>
                    </td>
                    <td>
                        <spring:url value="/admin/order/${order.id}/delete" var="deleteUrl" />
                        <button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Delete</button>
                                <%--<c:if test="${order.status == 'closed'}">disabled="disabled"</c:if>>Delete</button>--%>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
