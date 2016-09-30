<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Add new order</title>
</head>
<body>
<script>
    function onClick() {
        window.location = "/ordersStructure"
    }
</script>
<div class="container">
    <h2>Add new order</h2>
    <form:form action="/admin/addOrUpdateOrder" modelAttribute="Order" method="POST" class="form-horizontal">

        <spring:bind path="employeeId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Waiter ID:</label>
                <div class="col-sm-10">
                    <form:select  path="employeeId">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfEmployee}" multiple="false" itemLabel="id" itemValue="id"></form:options>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="dishes">
            <div class="form-group">
                <label class="col-sm-2 control-label">Dishes:</label>
                <div class="col-sm-10">
                    <form:select  path="dishes">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfDishes}" multiple="true" itemLabel="name" itemValue="name"></form:options>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="tableNumber">
            <div class="form-group">
                <label class="col-sm-2 control-label">Table number:</label>
                <div class="col-sm-10">
                    <form:input path="tableNumber" type="text" class="form-control " id="tableNumber"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="date">
            <div class="form-group">
                <label class="col-sm-2 control-label">Date:</label>
                <div class="col-sm-10">
                    <form:input path="date" type="text" class="form-control " id="date"
                                placeholder="Date"/>
                    <br>
                </div>
            </div>
        </spring:bind>

        < <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input type="submit" class="btn btn-success" value="Submit">
            <input type="reset" class="btn btn-warning" value="Reset"/>
            <input type="button" class="btn btn-danger" value="Cancel" onclick="onClick()">
        </div>
    </div>
    </form:form>
</div>
</body>
</html>
