<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Staff</title>
</head>
<body>
<div class="container">
    <h3>Our team</h3>
    <table class="table table-striped"">
        <tr>
            <td>
                <img src='<c:url value="/img/logotype/logotype_color3.jpg"/>' height="100" width="100"/>
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
                <h3>Waiters</h3>
                <table class="table table-striped">
                    <c:forEach items="${ListOfEmployee}" var="employee">
                    <c:if test="${employee.position.name=='waiter'}">
                        <tr>
                            <td>
                                <img src="/img/waiters/${employee.name}.jpg" alt="${employee.name}"
                                     width="30" height="30" hspace="10"/>
                            </td>
                            <td>${employee.name}</td>
                        </tr>
                    </c:if>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
