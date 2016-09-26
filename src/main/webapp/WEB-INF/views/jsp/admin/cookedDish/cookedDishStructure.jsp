<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Kitchen operations</title>
</head>
<body>
<div class="container">
    <h2>Select operation:</h2>
    <p><a href="/admin/addCookedDish">Add cooked dish</a></p>
    <p><a href="/admin/getCookedDishes">Get all cooked dish</a></p>
</div>
</body>
</html>
