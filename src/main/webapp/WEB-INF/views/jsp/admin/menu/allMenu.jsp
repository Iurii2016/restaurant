<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>All menu</title>
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
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Menu information</h2>
    <table class="table table-striped" style="align-items: center">
        <thead>
        <tr>
            <th>ID</th>
            <th>Menu name</th>
            <th>Dish</th>
            <th>Action</th>
        </tr>
        <tr>
            <th><input type="text" id="inputID" onkeyup="myFunction('inputID', 0)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputName" onkeyup="myFunction('inputName', 1)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputDish" onkeyup="myFunction('inputDish', 2)" placeholder="Search.." title="Type in a name"></th>
            <th colspan="2"><button id="newMenu"class="btn btn-success" onclick="location.href='/addMenu'">Add new menu</button></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ListOfMenu}" var="menu">
            <tr>
                <td>${menu.id}</td>
                <td>${menu.menuNameId.name}</td>
                <td>${menu.dishId.name}</td>
                <td>
                    <spring:url value="/menu/${menu.id}/update" var="updateUrl" />
                    <spring:url value="/menu/${menu.menuNameId.name}/delete" var="deleteUrl" />
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
