<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Grill'age</title>
    <style>
        * {
            box-sizing: border-box;
        }

        #inputName {
            background-image: url('/img/search-icon.png');
            background-position: 10px 10px;
            background-repeat: no-repeat;
            width: 100%;
            font-size: 16px;
            padding: 12px 20px 12px 40px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>Restaurant</h3>
    <table class="table table-striped" style="align-items: center">
        <tr>
            <td>
                <img src='<c:url value="/img/logotype/logotype_color.jpg"/>' height="100" width="100"/>
                <h2>Grill'age</h2>
                <p>Address: Gorkogo st., 72</p>
                <p>tel.: +38(044)44-000-44</p>
                <p>E-mail: <a href="mailto:grillage@gmail.com">grillage@gmail.com</a></p>
                <p>
                    <a href="https://uk-ua.facebook.com/"><img src='<c:url value="/img/fb.png"/>' height="20" width="20"/></a>
                    <a href="https://www.instagram.com/"><img src='<c:url value="/img/instagram.png"/>' height="25" width="25"/></a>
                </p>
            </td>
            <td>
                <h3>Menu</h3>
                <input type="text" id="inputName" onkeyup="myFunction('inputName', 0)" placeholder="Search for name.." title="Type in a name">
                <table id="myTable" class="table table-striped" style="align-items: center">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Weight</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ListOfDishes}" var="dish">
                        <tr>
                            <td><a href="/client/dishInfo?dish=${dish.name}"/> ${dish.name}</td>
                            <td>${dish.price}</td>
                            <td>${dish.weight}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>