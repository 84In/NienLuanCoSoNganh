<%-- 
    Document   : login
    Created on : 11 Feb 2024, 01:37:16
    Author     : ACER
--%>

<%-- 
    Document   : login
    Created on : 8 Feb 2024, 21:22:58
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Form</title>
        <link rel="stylesheet" href="<c:url value="/static/css/style.css" />">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    </head>
    <body>
        <div class="container">
            <div class="wrapper">
                <div class="title">
                    <span>Login Chat Web</span>
                </div>
                <form action="<c:url value="/login" />" method="POST">
                    <div class="row">
                        <i class="fas fa-user"></i>
                        <input
                            type="text"
                            placeholder="Username"
                            name="username"
                            maxlength="50"
                            required
                        >
                    </div>
                    <div class="row">
                        <i class="fas fa-lock"></i>
                        <input
                            type="password"
                            placeholder="Password"
                            name="password"
                            minlength="8"
                            maxlength="50"
                            required
                        >
                    </div>
                    <div class="row button">
                        <input type="submit" value="Login">
                    </div>
                    <div class="signup-link">
                        Not a member?
                        <a href="<c:url value="/register" />" > Signup now</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>

