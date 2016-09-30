<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        * {
            box-sizing: border-box;
        }

        #inputID, #inputIngredient, #inputQuantity, #inputUnit {
            background-image: url('/img/search-icon.png');
            background-position: 10px 10px;
            background-repeat: no-repeat;
            width: 100%;
            padding: 8px 0px 8px 40px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
        #newIngredient {
            width: 100%;
            padding: 8px 0px 8px 0px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
    </style>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Warehouse</title>
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
    <div>
        <h2>Warehouse balance</h2>
    </div>
    <table id="myTable" class="table table-striped" style="align-items: center">
        <thead>
        <tr>
            <th>ID <button onclick="location.href='/admin/warehouse/orderBy/${"id"}'"><i class="fa fa-caret-down"/></button></th>
            <th>Ingredient <button onclick="location.href='/admin/warehouse/orderBy/${"ingredientId"}'"><i class="fa fa-caret-down"/></button></th>
            <th>Quantity <button onclick="location.href='/admin/warehouse/orderBy/${"quantity"}'"><i class="fa fa-caret-down"/></button></th>
            <th>Unit <button onclick="location.href='/admin/warehouse/orderBy/${"unit"}'"><i class="fa fa-caret-down"/></button></th>
            <th colspan="2">Action</th>
        </tr>
        <tr>
            <th><input type="text" id="inputID" onkeyup="myFunction('inputID', 0)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputIngredient" onkeyup="myFunction('inputIngredient', 1)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputQuantity" onkeyup="myFunction('inputQuantity', 2)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputUnit" onkeyup="myFunction('inputUnit', 3)" placeholder="Search.." title="Type in a name"></th>
            <th colspan="2"><button id="newIngredient"class="btn btn-success" onclick="location.href='/admin/addWarehouse'">Add new menu</button></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${warehouseBalance}" var="warehouse">
            <tr>
                <td>${warehouse.id}</td>
                <td>${warehouse.ingredientId.ingredient}</td>
                <td>${warehouse.quantity}</td>
                <td>${warehouse.unit}</td>
                <td>
                    <spring:url value="/admin/warehouse/${warehouse.id}/update" var="updateUrl" />
                    <button class="btn btn-info" onclick="location.href='${updateUrl}'">Update</button>
                </td>
                <td>
                    <spring:url value="/admin/warehouse/${warehouse.ingredientId.ingredient}/delete" var="deleteUrl" />
                    <button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
