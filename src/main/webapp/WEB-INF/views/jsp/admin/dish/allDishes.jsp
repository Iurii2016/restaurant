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
            <th>ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Price</th>
            <th>Weight</th>
            <th></th>
            <th></th>
        </tr>
        <tr>
            <th><input type="text" id="inputID" onkeyup="myFunction('inputID', 0)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputName" onkeyup="myFunction('inputName', 1)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputCategory" onkeyup="myFunction('inputCategory', 2)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputPrice" onkeyup="myFunction('inputPrice', 3)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputWeight" onkeyup="myFunction('inputWeight', 4)" placeholder="Search.." title="Type in a name"></th>
            <th colspan="2"><button id="newDish" class="btn btn-success" onclick="location.href='/addDish'">Add new menu</button></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ListOfDishes}" var="dish">
            <tr>
                <td>${dish.id}</td>
                <td>${dish.name}</td>
                <td>${dish.categoryId.name}</td>
                <td>${dish.price}</td>
                <td>${dish.weight}</td>
                <td>
                    <spring:url value="/dish/${dish.id}/update" var="updateUrl" />
                    <button class="btn btn-info" onclick="location.href='${updateUrl}'">Update</button>
                </td>
                <td>
                    <spring:url value="/dish/${dish.name}/delete" var="deleteUrl" />
                    <button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
