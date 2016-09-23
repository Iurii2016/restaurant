<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Add dish ingredient</title>
</head>
<body>
<script>
    function onClick() {
        window.location = "/dishStructure"
    }
</script>
<div class="container">
    <h2>Add new dish ingredient</h2>
    <form:form action="/addNewDishIngredient" commandName="newDishIngredient" method="POST" class="form-horizontal">

        <spring:bind path="dishId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Dish:</label>
                <div class="col-sm-10">
                    <form:select  path="dishId">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfDishes}" itemLabel="name" itemValue="name"></form:options>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="ingredientId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Ingredient:</label>
                <div class="col-sm-10">
                    <form:select  path="ingredientId">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfIngredients}" itemLabel="ingredient" itemValue="ingredient"></form:options>
                    </form:select>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="quantity">
            <div class="form-group">
                <label class="col-sm-2 control-label">Quantity:</label>
                <div class="col-sm-10">
                    <form:input path="quantity" type="text" class="form-control " id="quantity"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="unit">
            <div class="form-group">
                <label class="col-sm-2 control-label">Unit:</label>
                <div class="col-sm-5">
                    <form:select  path="unit" multiple="false">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfUnits}"></form:options>
                    </form:select>
                </div>
                <div class="col-sm-5"></div>
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

