<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Add Dish</title>
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
        #categorySelect{
            width: 100%;
            padding: 7px 0px 7px 0px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
    </style>
</head>
<body>
<script>
    function onClick() {
        window.location = "/admin/getAllDishes"
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
    <form:form action="/admin/addOrUpdateDish" modelAttribute="dish" method="POST" class="form-horizontal">

        <form:hidden path="id" />

        <spring:bind path="name">
            <div class="form-group">
                <label class="col-sm-2 control-label">Dish name:</label>
                <div class="col-sm-10">
                    <form:input path="name" type="text" class="form-control " id="name" placeholder="Name"/>
                    <form:errors path="name" cssClass="error" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="categoryId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Category:</label>
                <div class="col-sm-10">
                    <form:select  path="categoryId" id="categorySelect">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfCategories}" itemLabel="name" itemValue="name"/>
                    </form:select>
                    <form:errors path="categoryId" cssClass="error" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="price">
            <div class="form-group">
                <label class="col-sm-2 control-label">Price:</label>
                <div class="col-sm-10">
                    <form:input path="price" type="text" class="form-control " id="price"/>
                    <form:errors path="price" cssClass="error" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="weight">
            <div class="form-group">
                <label class="col-sm-2 control-label">Weight:</label>
                <div class="col-sm-10">
                    <form:input path="weight" type="text" class="form-control " id="weight"/>
                    <form:errors path="weight" cssClass="error" />
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
