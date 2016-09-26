<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <title>Menu operations</title>
</head>
<body>
<div class="container">
    <h2>Select operation:</h2>
    <p><a href="/admin/addMenu">Add menu</a></p>
    <p><a href="/admin/getAllMenu">Get all menu</a></p>
    <%--<form action="/getMenuByName/" method="get">--%>
        <%--<p>--%>
            <%--Enter menu name: <input name="getMenuByName" type="text" size="40">--%>
            <%--<input type="submit" class="btn btn-info" value="Get menu by name">--%>
        <%--</p>--%>
    <%--</form>--%>
    <%--<form action="/deleteDishesByMenuName/" method="get">--%>
        <%--<p>--%>
            <%--Enter menu name: <input name="deleteDishesByMenuName" type="text" size="40">--%>
            <%--<input type="submit" class="btn btn-danger" value="Delete menu by name">--%>
        <%--</p>--%>
    <%--</form>--%>
    <%--<form action="/deleteDishFromMenuByName/" method="get">--%>
        <%--<p>--%>
            <%--Enter dish name: <input name="deleteDishFromMenuByName" type="text" size="40">--%>
            <%--<input type="submit" class="btn btn-danger" value="Delete dish from all menu by name">--%>
        <%--</p>--%>
    <%--</form>--%>
</div>
</body>
</html>
