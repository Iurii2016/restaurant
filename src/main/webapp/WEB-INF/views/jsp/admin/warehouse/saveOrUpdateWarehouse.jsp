<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Add new Ingredient to warehouse</title>
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
        #ingredientSelect, #unitSelect{
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
        window.location = "/admin/warehouse/orderBy/id"
    }
</script>
<div class="container">

    <c:choose>
        <c:when test="${warehouse['new']}">
            <h2>Add new ingredient to warehouse</h2>
        </c:when>
        <c:otherwise>
            <h2>Update ingredient</h2>
        </c:otherwise>
    </c:choose>

    <form:form action="/admin/saveOrUpdateWarehouse" modelAttribute="warehouse" method="POST" class="form-horizontal">

        <form:hidden path="id" />

        <spring:bind path="ingredientId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Ingredient:</label>
                    <div class="col-sm-10">
                        <form:select path="ingredientId" id="ingredientSelect">
                            <form:option value="NONE">--SELECT--</form:option>
                            <form:options items="${listOfIngredients}" multiple="false" itemLabel="ingredient" itemValue="ingredient"/>
                        </form:select>
                        <form:errors path="ingredientId" cssClass="error" />
                    </div>
            </div>
        </spring:bind>

        <spring:bind path="quantity">
            <div class="form-group">
                <label class="col-sm-2 control-label">Quantity:</label>
                <div class="col-sm-10">
                    <form:input path="quantity" type="number" class="form-control " id="quantity"/>
                    <form:errors path="quantity" cssClass="error" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="unit">
            <div class="form-group">
                <label class="col-sm-2 control-label">Unit:</label>
                <div class="col-sm-10">
                    <form:select  path="unit" id="unitSelect">
                        <form:options items="${listOfUnits}"/>
                    </form:select>
                </div>
                <div class="col-sm-5"></div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${warehouse['new']}">
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
