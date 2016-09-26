<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        * {
            box-sizing: border-box;
        }

        #inputID, #inputSurname, #inputMame, #inputBirthday, #inputPhone , #inputSalary, #inputPosition, #newEmployee{
            width: 100%;
            padding: 8px 0px 8px 2px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
    </style>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Staff</title>
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
    <h2>Staff information</h2>
    </div>
    <table id="myTable" class="table table-striped" style="align-items: center">
        <thead>
        <tr>
            <th>ID</th>
            <th>Surname</th>
            <th>Name</th>
            <th>Birthday</th>
            <th>Phone</th>
            <th>Salary</th>
            <th>Position</th>
            <th colspan="2">Action</th>
        </tr>
        <tr>
            <th><input type="text" id="inputID" onkeyup="myFunction('inputID', 0)" placeholder="Search.." title="Type in an ID"></th>
            <th><input type="text" id="inputSurname" onkeyup="myFunction('inputSurname', 1)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputMame" onkeyup="myFunction('inputMame', 2)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputBirthday" onkeyup="myFunction('inputBirthday', 3)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputPhone" onkeyup="myFunction('inputPhone', 4)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputSalary" onkeyup="myFunction('inputSalary', 5)" placeholder="Search.." title="Type in a name"></th>
            <th><input type="text" id="inputPosition" onkeyup="myFunction('inputPosition', 6)" placeholder="Search.." title="Type in a name"></th>
            <th colspan="2"><button id="newEmployee"class="btn btn-success" onclick="location.href='/admin/addEmployee'">Add new employee</button></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ListOfEmployee}" var="employee">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.surname}</td>
                <td>${employee.name}</td>
                <td>${employee.birthday}</td>
                <td>${employee.phoneNumber}</td>
                <td>${employee.salary}</td>
                <td>${employee.position.name}</td>
                <td>
                    <spring:url value="/admin/employee/${employee.id}/update" var="updateUrl" />
                    <button class="btn btn-info" onclick="location.href='${updateUrl}'">Update</button>
                </td>
                <td>
                    <spring:url value="/admin/employee/${employee.id}/delete" var="deleteUrl" />
                    <button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
