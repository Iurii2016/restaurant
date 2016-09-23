<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Add new Employee</title>
</head>
<body>
<script>
    function onClick() {
        window.location = "/employeeStructure"
    }
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
    <form:form action="/addOrUpdateEmployee" commandName="employee" method="POST" class="form-horizontal">

        <form:hidden path="id" />

        <spring:bind path="surname">
            <div class="form-group">
                <label class="col-sm-2 control-label">Surname:</label>
                <div class="col-sm-10">
                    <form:input path="surname" type="text" class="form-control " id="surname"
                                placeholder="Surname"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="name">
            <div class="form-group">
                <label class="col-sm-2 control-label">Name:</label>
                <div class="col-sm-10">
                    <form:input path="name" type="text" class="form-control " id="name"
                                placeholder="Name"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="birthday">
            <div class="form-group">
                <label class="col-sm-2 control-label">Birthday:</label>
                <div class="col-sm-10">
                    <form:input path="birthday" type="text" class="form-control " id="birthday"
                                placeholder="Birthday"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="phoneNumber">
            <div class="form-group">
                <label class="col-sm-2 control-label">Phone number:</label>
                <div class="col-sm-10">
                    <form:input path="phoneNumber" type="text" class="form-control " id="phoneNumber"
                                placeholder="Phone number"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="salary">
            <div class="form-group">
                <label class="col-sm-2 control-label">Salary:</label>
                <div class="col-sm-10">
                    <form:input path="salary" class="form-control " id="salary"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="position">
            <div class="form-group">
                <label class="col-sm-2 control-label">Position:</label>
                <div class="col-sm-5">
                    <form:select  path="position">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfPositions}" itemLabel="name" itemValue="name"></form:options>
                    </form:select>
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
                <input type="button" class="btn btn-danger" value="Cancel" onclick="onClick()">
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
