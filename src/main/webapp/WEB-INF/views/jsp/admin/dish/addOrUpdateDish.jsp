<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Add Dish</title>
</head>
<body>
<script>
    function onClick() {
        window.location = "/dishStructure"
    }
</script>
<div class="container">
    <c:choose>
        <c:when test="${dish['new']}">
            <h2>Add new dish</h2>
        </c:when>
        <c:otherwise>
            <h2>Update dish</h2>
        </c:otherwise>
    </c:choose>
    <form:form action="/addOrUpdateDish" commandName="dish" method="POST" class="form-horizontal">

        <form:hidden path="id" />

        <spring:bind path="name">
            <div class="form-group">
                <label class="col-sm-2 control-label">Dish name:</label>
                <div class="col-sm-10">
                    <form:input path="name" type="text" class="form-control " id="name"
                                placeholder="Name"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="categoryId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Category:</label>
                <div class="col-sm-10">
                    <form:select  path="categoryId">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfCategories}" itemLabel="name" itemValue="name"></form:options>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="price">
            <div class="form-group">
                <label class="col-sm-2 control-label">Price:</label>
                <div class="col-sm-10">
                    <form:input path="price" type="text" class="form-control " id="price"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="weight">
            <div class="form-group">
                <label class="col-sm-2 control-label">Weight:</label>
                <div class="col-sm-10">
                    <form:input path="weight" type="text" class="form-control " id="weight"/>
                    <br>
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${dish['new']}">
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
