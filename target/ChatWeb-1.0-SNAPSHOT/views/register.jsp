<%-- 
    Document   : register
    Created on : 8 Feb 2024, 21:26:06
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register Form</title>
        <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    </head>
    <body>
        <div class="container register">
            <div class="wrapper">
                <div class="title">
                    <span>Register Chat Web</span>
                </div>
                <form action="<c:url value="/register" />" method="POST" enctype="multipart/form-data">
                    <div class="row">
                        <i class="fas fa-user"></i>
                        <input
                            type="text"
                            placeholder="Username"
                            name="username"
                            id="username"
                            maxlength="50"
                            required
                        >
                    </div>
                    <div class="row">
                        <i class="fa-solid fa-person"></i>
                        <input
                            type="text"
                            placeholder="Name"
                            name="name"
                            id="name"
                            maxlength="150"
                            required
                        >
                    </div>
                    <div class="row">
                        <i class="fas fa-lock"></i>
                        <input
                            type="password"
                            placeholder="Password"
                            name="password"
                            id="password"
                            minlength="8"
                            maxlength="50"
                            required
                        >
                    </div>
                    <div class="row">
                        <i class="fas fa-lock"></i>
                        <input
                            type="password"
                            placeholder="Confirm Password"
                            id="confirmPassword"
                            minlength="8"
                            maxlength="50"
                            required
                        >
                    </div>
                    <div class="row">
                        <i class="fa fa-image"></i>
                        <input
                            type="file"
                            id="avatar"
                            name="avatar"
                            accept=".jpg, .png, .jpeg, .gif, .raw"
                            required
                        >
                    </div>
                    <div class="row button">
                        <input type="submit" value="Register" onclick="return checkValidation()">
                    </div>
                    <div class="signup-link">
                        Are you a member?
                        <a href="<c:url value="/login" />">Login</a>
                    </div>
                </form>
            </div>
        </div>
        <script>
            function checkValidation(){
                let password = document.getElementById("password");
                let confirmPassword = document.getElementById("confirmPassword");

                let passValue = password.value ;
                let comfirmPassValue = confirmPassword.value;
                if(passValue != comfirmPassValue) {
                    window.alert('Passwords are inconsistent');
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>