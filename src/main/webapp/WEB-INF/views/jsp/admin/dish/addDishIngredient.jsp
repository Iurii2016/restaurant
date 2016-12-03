<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Add ingredient to dish</title>
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
        #ingredientSelect, #dishSelect, #unitSelect{
            width: 100%;
            padding: 7px 0px 7px 0px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
    </style>
</head>
<body>
<script>
    function backToDishes() {
        window.location = "/admin/dish/orderBy/id"
    };
    function validateForm() {
        $("span").html("");
        var valid = true;
        var ingredient = $("#ingredientSelect").val();
        var quantity = $("#quantity").val();
        if(ingredient == "NONE"){
            $('#ingredientErr').html("Ingredient must be selected");
            valid = false;
        }
        if (quantity == 0 ){
            $('#quantityErr').html("Quantity can't be zero");
            valid = false;
        }
        if (valid===true){
            document.forms["myForm"].submit();
        }
    };
</script>
<div class="container">
    <h2>Add new ingredient to dish</h2>
    <form:form onsubmit="validateForm();return false;" action="/admin/saveOrUpdateDishIngredient" modelAttribute="dishIngredient" method="POST" class="form-horizontal">

        <spring:bind path="dishId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Dish:</label>
                <div class="col-sm-10">
                    <form:select  path="dishId" id="dishSelect">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfDishes}" itemLabel="name" itemValue="name"/>
                    </form:select>
                    <form:errors path="dishId" cssClass="error"/>
                    <span class="error" id="dishErr"></span>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="ingredientId">
            <div class="form-group">
                <label class="col-sm-2 control-label">Ingredient:</label>
                <div class="col-sm-10">
                    <form:select  path="ingredientId" id="ingredientSelect">
                        <form:option value="NONE"> --SELECT--</form:option>
                        <form:options items="${listOfIngredients}" itemLabel="ingredient" itemValue="ingredient"/>
                    </form:select>
                    <form:errors path="ingredientId" cssClass="error" />
                    <span class="error" id="ingredientErr"></span>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="quantity">
            <div class="form-group">
                <label class="col-sm-2 control-label">Quantity:</label>
                <div class="col-sm-10">
                    <form:input path="quantity" type="number" class="form-control " id="quantity" value="0"/>
                    <form:errors path="quantity" cssClass="error" />
                    <span class="error" id="quantityErr"></span>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="unit">
            <div class="form-group">
                <label class="col-sm-2 control-label">Unit:</label>
                <div class="col-sm-5">
                    <form:select  path="unit" id="unitSelect">
                        <form:options items="${listOfUnits}"/>
                    </form:select>
                    <form:errors path="unit" cssClass="error" />
                    <span class="error" id="unitErr"></span>
                </div>
                <div class="col-sm-5"></div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="submit" class="btn btn-success" value="Submit">
                <input type="reset" class="btn btn-warning" value="Reset"/>
                <input type="button" class="btn btn-danger" value="Cancel" onclick="backToDishes()">
            </div>
        </div>

    </form:form>
</div>
</body>
</html>

