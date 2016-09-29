<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Spring Security</title>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-md-4 col-md-offset-4">
                <p align="center"><img class="profile-img" src="/img/logotype/logotype_black.jpg" width="150" height="150"/></p>
                <h2 class="text-center login-title">Log in to Grill'age</h2>
                <div class="account-wall">
                    <c:url value="/j_spring_security_check" var="loginUrl" />
                    <form method="post" action="${loginUrl}">
                        <input type="text" name="j_username" class="form-control" placeholder="Email" required autofocus>
                        <input type="password" name="j_password" class="form-control" placeholder="Password" required>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">
                            Sign in</button>
                            <input type="checkbox"  name="_spring_security_remember_me" value="remember-me">
                            Remember me
                    </form>
                </div>
                <a href="/" class="text-center new-account">Go to home page </a>
            </div>
        </div>
    </div>
</body>
</html>