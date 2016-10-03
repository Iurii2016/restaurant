<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Add new cooked dish</title>
</head>
<body>
<script>
    function onClick() {
        window.location = "/admin/getCookedDishes"
    }
</script>
<div class="container">
    <h2>Add new cooked dish</h2>
    <form:form action="/admin/addNewCookedDish" commandName="newCookedDish" method="POST" class="form-horizontal">

        <spring:bind path="dishId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Dish:</label>
                <div class="col-sm-10">
                    <form:select  path="dishId">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfDishes}" multiple="false" itemLabel="name" itemValue="name"></form:options>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="employeeId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Cook ID:</label>
                <div class="col-sm-10">
                    <form:select  path="employeeId">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfEmployees}" multiple="false" itemLabel="id" itemValue="id"></form:options>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="orderId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Order ID:</label>
                <div class="col-sm-10">
                    <form:select  path="orderId">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfOrders}" multiple="false" itemLabel="id" itemValue="id"></form:options>
                    </form:select>
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

        <div class="form-group">
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
