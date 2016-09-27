<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Tables</title>
</head>
<body>
<div class="container">
    <h3>Booking</h3>
    <table class="table table-striped" style="align-items: center">
        <tr>
            <td>
                <img src='<c:url value="/img/logotype/logotype_color2.jpg"/>' height="100" width="100"/>
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
                <img src='<c:url value="/img/shema.png"/>' height="450" width="650"/>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
