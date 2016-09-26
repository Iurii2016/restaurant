<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        * {
            box-sizing: border-box;
        }

        #myInput {
            background-image: url('/img/search-icon.png');
            background-position: 10px 10px;
            background-repeat: no-repeat;
            width: 100%;
            font-size: 16px;
            padding: 12px 20px 12px 40px;
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
    <input type="text" id="myInput" onkeyup="myFunction('myInput', 1)" placeholder="Search for ingredient.." title="Type in a name">

    <table id="myTable" class="table table-striped" style="align-items: center">
        <thead>
        <tr>
            <th>ID</th>
            <th>Ingredient</th>
            <th>Quantity</th>
            <th>Unit</th>
            <th>Action</th>
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
                    <spring:url value="/admin/warehouse/${warehouse.ingredientId.ingredient}/delete" var="deleteUrl" />
                    <button class="btn btn-info" onclick="location.href='${updateUrl}'">Update</button>
                    <button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
