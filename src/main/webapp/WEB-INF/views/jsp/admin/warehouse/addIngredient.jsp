<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Add new Ingredient into the reference book</title>
</head>
<body>
<script>
    function onClick() {
        window.location = "/warehouseStructure"
    }
</script>
<div class="container">

    <h2>Add new ingredient into the reference book</h2>

    <form:form action="/newIngredient" commandName="newIngredient" method="POST"
               class="form-horizontal">

        <spring:bind path="ingredient">
            <div class="form-group">
                <label class="col-sm-2 control-label">Ingredient:</label>
                <div class="col-sm-10">
                    <form:input path="ingredient" type="text" class="form-control " id="ingredient"
                                placeholder="ingredient"/>
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
