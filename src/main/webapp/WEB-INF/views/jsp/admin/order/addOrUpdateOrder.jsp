<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Add new order</title>
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
        #waiterSelect, #dishSelect{
            width: 100%;
            padding: 7px 0px 7px 0px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
    </style>
</head>
<body>
<script>
    function backToOrders() {
        window.location = "/admin/order/orderBy/id"
    };
    function validateForm() {
        $("span").html("");
        var valid = true;
        var waiter = $("#waiterSelect").val();
        var dish = $("#dishSelect").val();
        var table = $("#tableNumber").val();
        var date = $("#date").val();
        if(waiter == 0){
            $('#employeeErr').html("Waiter must be selected");
            valid = false;
        }
        if (dish == null ){
            $('#dishesErr').html("Dish must be selected");
            valid = false;
        }
        if (table == 0 ){
            $('#tableErr').html("Table can't be zero");
            valid = false;
        }
        if (date == "" ){
            $('#dateErr').html("Date must be filled out");
            valid = false;
        }
        if (valid===true){
            document.forms["myForm"].submit();
        }
    };
</script>
<div class="container">

    <c:choose>
        <c:when test="${order['new']}">
            <h2>Add new order</h2>
        </c:when>
        <c:otherwise>
            <h2>Update order</h2>
        </c:otherwise>
    </c:choose>

    <form:form onsubmit="validateForm(); return false;" action="/admin/addOrUpdateOrder" modelAttribute="order" method="POST" class="form-horizontal">

        <form:hidden path="id" />

        <spring:bind path="employeeId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Waiter ID:</label>
                <div class="col-sm-10">
                    <form:select  path="employeeId" id="waiterSelect">
                        <form:option value="0">--SELECT--</form:option>
                        <form:options items="${listOfEmployee}" multiple="false" itemLabel="id" itemValue="id"/>
                    </form:select>
                    <form:errors path="employeeId" cssClass="error" />
                    <span class="error" id="employeeErr"></span>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="dishes">
            <div class="form-group">
                <label class="col-sm-2 control-label">Dishes:</label>
                <div class="col-sm-10">
                    <form:select  path="dishes" id="dishSelect">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfDishes}" multiple="true" itemLabel="name" itemValue="name"/>
                    </form:select>
                    <form:errors path="dishes" cssClass="error" />
                    <span class="error" id="dishesErr"></span>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="tableNumber">
            <div class="form-group">
                <label class="col-sm-2 control-label">Table number:</label>
                <div class="col-sm-10">
                    <form:input path="tableNumber" type="number" class="form-control " id="tableNumber" value="0"/>
                    <form:errors path="tableNumber" cssClass="error" />
                    <span class="error" id="tableErr"></span>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="date">
            <div class="form-group">
                <label class="col-sm-2 control-label">Date:</label>
                <div class="col-sm-10">
                    <form:input path="date" type="text" class="form-control " id="date" placeholder="yyyy-mm-dd"/>
                    <form:errors path="date" cssClass="error" />
                    <span class="error" id="dateErr"></span>
                    <br>
                </div>
            </div>
        </spring:bind>

        <form:hidden path="status" />

        <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <c:choose>
                <c:when test="${order['new']}">
                    <input type="submit" class="btn btn-success" value="Submit"/>
                    <input type="reset" class="btn btn-warning" value="Reset"/>
                </c:when>
                <c:otherwise>
                    <input type="submit" class="btn btn-success" value="Update"/>
                </c:otherwise>
            </c:choose>
            <input type="button" class="btn btn-danger" value="Cancel" onclick="backToOrders()">
        </div>
    </div>
    </form:form>
</div>
</body>
</html>
