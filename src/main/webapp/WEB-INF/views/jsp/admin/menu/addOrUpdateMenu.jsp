<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Add new menu</title>
</head>
<body>
<script>
    function onClick() {
        window.location = "/admin/menuStructure"
    }
</script>
<div class="container">
    <c:choose>
        <c:when test="${menu['new']}">
            <h2>Add new menu</h2>
        </c:when>
        <c:otherwise>
            <h2>Update menu</h2>
        </c:otherwise>
    </c:choose>

    <form:form action="/admin/addOrUpdateMenu" commandName="menu" method="POST" class="form-horizontal">

        <form:hidden path="id" />

        <spring:bind path="menuNameId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Menu name:</label>
                <div class="col-sm-10">
                    <form:select  path="menuNameId">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfMenuNames}" multiple="false" itemLabel="name" itemValue="name"></form:options>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="dishId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Dish:</label>
                <div class="col-sm-10">
                    <form:select  path="dishId">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfDishes}" multiple="false" itemLabel="name" itemValue="name"></form:options>
                    </form:select>
                    <br>
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${menu['new']}">
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
