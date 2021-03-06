<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Add new Employee</title>
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
        #positionSelect{
            width: 100%;
            padding: 7px 0px 7px 0px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
    </style>
</head>
<body>
<script>
    function backToEmployees() {
        window.location = "/admin/employee/orderBy/id"
    };
    function validateForm() {
        $("span").html("");
        var valid = true;
        var surname = $("#surname").val();
        var name = $("#name").val();
        var position = $("#positionSelect").val();
        if (surname == "") {
            $('#surnameErr').html("Surname must be filled out");
            valid = false;
        }
        if(name == ""){
            $('#nameErr').html("Name must be filled out");
            valid = false;
        }
        if (position == "NONE" ){
            $('#positionErr').html("Position must be selected");
            valid = false;
        }
        if (valid===true){
            document.forms["myForm"].submit();
        }
    };
</script>
<div class="container">
    <c:choose>
        <c:when test="${employee['new']}">
            <h2>Add new Employee</h2>
        </c:when>
        <c:otherwise>
            <h2>Update Employee</h2>
        </c:otherwise>
    </c:choose>
    <form:form name="myForm" onsubmit="validateForm();return false;" action="/admin/addOrUpdateEmployee" modelAttribute="employee" method="POST" class="form-horizontal">

        <form:hidden path="id" />

        <spring:bind path="surname">
            <div class="form-group">
                <label class="col-sm-2 control-label">Surname:</label>
                <div class="col-sm-10">
                    <form:input path="surname" type="text" class="form-control " id="surname" placeholder="Surname"/>
                    <form:errors path="surname" cssClass="error"/>
                    <span class="error" id="surnameErr"></span>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="name">
            <div class="form-group">
                <label class="col-sm-2 control-label">Name:</label>
                <div class="col-sm-10">
                    <form:input path="name" type="text" class="form-control " id="name" placeholder="Name"/>
                    <form:errors path="name" cssClass="error" />
                    <span class="error" id="nameErr"></span>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="birthday">
            <div class="form-group">
                <label class="col-sm-2 control-label">Birthday:</label>
                <div class="col-sm-10">
                    <form:input path="birthday" type="text" class="form-control " id="birthday" placeholder="yyyy-mm-dd"/>
                    <form:errors path="birthday" cssClass="error" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="phoneNumber">
            <div class="form-group">
                <label class="col-sm-2 control-label">Phone number:</label>
                <div class="col-sm-10">
                    <form:input path="phoneNumber" type="text" class="form-control " id="phoneNumber"
                                placeholder="Phone number"/>
                    <form:errors path="phoneNumber" cssClass="error" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="salary">
            <div class="form-group">
                <label class="col-sm-2 control-label">Salary:</label>
                <div class="col-sm-10">
                    <form:input path="salary" class="form-control " id="salary" type="number" value="0"/>
                    <form:errors path="salary" cssClass="error" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="position">
            <div class="form-group">
                <label class="col-sm-2 control-label">Position:</label>
                <div class="col-sm-10">
                    <form:select  path="position" id="positionSelect">
                        <form:option value="NONE">--SELECT--</form:option>
                        <form:options items="${listOfPositions}" itemLabel="name" itemValue="name"/>
                    </form:select>
                    <form:errors path="position" cssClass="error" />
                    <span class="error" id="positionErr"></span>
                    <br>
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${employee['new']}">
                        <input type="submit" class="btn btn-success" value="Submit">
                        <input type="reset" class="btn btn-warning" value="Reset"/>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" class="btn btn-success" value="Update">
                    </c:otherwise>
                </c:choose>
                <input type="button" class="btn btn-danger" value="Cancel" onclick="backToEmployees()">
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
