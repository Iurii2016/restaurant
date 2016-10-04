<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Dishes</title>
    <style>
        * {
            box-sizing: border-box;
        }

        #inputID, #inputName, #inputCategory, #inputPrice, #inputWeight {
            background-image: url('/img/search-icon.png');
            background-position: 10px 10px;
            background-repeat: no-repeat;
            width: 100%;
            padding: 8px 0px 8px 40px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
        #newDish {
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
    <h2>Dishes information</h2>
    <table id="myTable" class="table table-striped" style="align-items: center">
        <thead>
        <tr>
            <th>ID <button onclick="location.href='/admin/dish/orderBy/${"id"}'"><i class="fa fa-caret-down"/></th>
            <th>Name <button onclick="location.href='/admin/dish/orderBy/${"name"}'"><i class="fa fa-caret-down"/></th>
            <th>Category <button onclick="location.href='/admin/dish/orderBy/${"categoryId"}'"><i class="fa fa-caret-down"/></th>
            <th>Price <button onclick="location.href='/admin/dish/orderBy/${"price"}'"><i class="fa fa-caret-down"/></th>
            <th>Weight <button onclick="location.href='/admin/dish/orderBy/${"weight"}'"><i class="fa fa-caret-down"/></th>
            <th colspan="2">Action</th>
        </tr>
        <tr>
            <th><input type="text" id="inputID" onkeyup="myFunction('inputID', 0)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputName" onkeyup="myFunction('inputName', 1)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputCategory" onkeyup="myFunction('inputCategory', 2)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputPrice" onkeyup="myFunction('inputPrice', 3)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputWeight" onkeyup="myFunction('inputWeight', 4)" placeholder="Search.." title="Type in a name"></th>
            <th colspan="2"><button id="newDish" class="btn btn-success" onclick="location.href='/admin/addDish'">Add new dish</button></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ListOfDishes}" var="dish">
            <tr>
                <td>${dish.id}</td>
                <td><a href="/admin/dish/${dish.name}"/>${dish.name}</td>
                <td>${dish.categoryId.name}</td>
                <td>${dish.price}</td>
                <td>${dish.weight}</td>
                <td>
                    <spring:url value="/admin/dish/${dish.id}/update" var="updateUrl" />
                    <button class="btn btn-info" onclick="location.href='${updateUrl}'">Update</button>
                </td>
                <td>
                    <spring:url value="/admin/dish/${dish.name}/delete" var="deleteUrl" />
                    <button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
