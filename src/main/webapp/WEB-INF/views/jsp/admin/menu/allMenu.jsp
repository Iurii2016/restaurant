<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Menu</title>
    <style>
        * {
            box-sizing: border-box;
        }

        #inputID, #inputName, #inputDish {
            background-image: url('/img/search-icon.png');
            background-position: 10px 10px;
            background-repeat: no-repeat;
            width: 100%;
            padding: 8px 0px 8px 40px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
        #newMenu {
            width: 100%;
            padding: 8px 0px 8px 0px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
    </style>
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
    <h2>Menu information</h2>
    <table id="myTable" class="table table-striped" style="align-items: center">
        <thead>
        <tr>
            <th>ID <button onclick="location.href='/admin/menu/orderBy/${"id"}'"><i class="fa fa-caret-down"/></th>
            <th>Menu name <button onclick="location.href='/admin/menu/orderBy/${"menuNameId"}'"><i class="fa fa-caret-down"/></th>
            <th>Dish <button onclick="location.href='/admin/menu/orderBy/${"dishId"}'"><i class="fa fa-caret-down"/></th>
            <th colspan="2">Action</th>
        </tr>
        <tr>
            <th><input type="text" id="inputID" onkeyup="myFunction('inputID', 0)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputName" onkeyup="myFunction('inputName', 1)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputDish" onkeyup="myFunction('inputDish', 2)" placeholder="Search.." title="Type in a name"></th>
            <th colspan="2"><button id="newMenu"class="btn btn-success" onclick="location.href='/admin/addMenu'">Add new menu</button></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ListOfMenu}" var="menu">
            <tr>
                <td>${menu.id}</td>
                <td>${menu.menuNameId.name}</td>
                <td>${menu.dishId.name}</td>
                <td>
                    <spring:url value="/admin/menu/${menu.id}/update" var="updateUrl" />
                    <button class="btn btn-info" onclick="location.href='${updateUrl}'">Update</button>
                </td>
                <td>
                    <spring:url value="/admin/menu/${menu.id}/delete" var="deleteUrl" />
                    <button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
