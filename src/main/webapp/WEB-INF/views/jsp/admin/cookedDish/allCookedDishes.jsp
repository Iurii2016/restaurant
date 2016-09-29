<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Kitchen</title>
    <style>
        * {
            box-sizing: border-box;
        }

        #inputID, #inputDish, #inputCook, #inputOrder, #inputDate, #inputDate {
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
    <h2>Cooked dish information</h2>
    <table id="myTable" class="table table-striped" style="align-items: center">
        <thead>
        <tr>
            <th>ID <button onclick="location.href='/admin/cookedDish/orderBy/${"id"}'"><i class="fa fa-caret-down"/></th>
            <th>Dish <button onclick="location.href='/admin/cookedDish/orderBy/${"dishId"}'"><i class="fa fa-caret-down"/></th>
            <th>Cook <button onclick="location.href='/admin/cookedDish/orderBy/${"employeeId"}'"><i class="fa fa-caret-down"/></th>
            <th>Order ID <button onclick="location.href='/admin/cookedDish/orderBy/${"orderId"}'"><i class="fa fa-caret-down"/></th>
            <th>Date <button onclick="location.href='/admin/cookedDish/orderBy/${"date"}'"><i class="fa fa-caret-down"/></th>
        </tr>
        <tr>
            <th><input type="text" id="inputID" onkeyup="myFunction('inputID', 0)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputDish" onkeyup="myFunction('inputDish', 1)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputCook" onkeyup="myFunction('inputCook', 2)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputOrder" onkeyup="myFunction('inputOrder', 3)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputDate" onkeyup="myFunction('inputDate', 4)" placeholder="Search.." title="Type in a name"></th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${ListOfCookedDishes}" var="coockedDish">
                <tr>
                    <td>${coockedDish.id}</td>
                    <td>${coockedDish.dishId.name}</td>
                    <td>${coockedDish.employeeId.name}</td>
                    <td>${coockedDish.orderId.id}</td>
                    <td>${coockedDish.date}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
