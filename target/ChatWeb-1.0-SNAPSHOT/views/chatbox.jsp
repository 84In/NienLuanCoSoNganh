<%-- 
    Document   : chatbox
    Created on : 11 Feb 2024, 15:39:03
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
        <link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>">
        <link rel="stylesheet" href="<c:url value="./static/css/chatbox.css"/>">
        <title>Chat App</title>
    </head>
    <body>
        <div class="container">
            <div class="left">
                <div class="header-left">
                    <img class="avatar-img" src="AvatarRestController?username=${user.username}" alt="avatar user">
                    <h1 id="username">${user.username}</h1>
                    <a href="<c:url value="/LogoutController"/>">
                        <i class="fa-solid fa-right-from-bracket"></i>
                    </a>

                </div>
                <div class="main-left"> 
                    <div class="title friends">
                        <span>
                            <strong>Bạn bè</strong>
                        </span>
                        <span>
                            <button id="acceptFriends" onclick="showTableAcceptFriends()">Lời mời kết bạn</button>
                        </span>
                        <span>
                            <button id="addFriends" onclick="showTableAddFriends()">Thêm bạn mới</button>
                        </span>
                    </div>
                    <div class="listfriend" id="listfriendsonline">
                        
                    </div>
                </div>
            </div>
            <div class="chat" id="receiver"><!--
                <!--
                <div class="chat-header clearfix">
                    <div class="header-right">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwYBrWUPdvDWKG7DAGmSaYD5S9li-LbeI6Xw&usqp=CAU" alt="avatar user">
                        <h1>Nguyễn Văn A</h1>
                        <div class="status online">Đang hoạt động</div>
                        <div class="close">
                            <i class="fa-regular fa-circle-xmark"></i>
                        </div>
                    </div>
                </div>
                <div class="chat-history">
                    <ul class="m-b-0">
                        <li class="clearfix">
                            <div class="message other-message float-right">s the project coming along?</div>
                            <div class="time float-right">
                                <div class="message-data-time">10:10</div>
                            </div>
                        </li>
                        <li class="clearfix">
                            <div class="message my-message">Are we meeting today?</div>
                            <div class="time">
                                <div class="message-data-time">10:10</div>
                            </div>
                        </li>
                        <li class="clearfix">
                            <div class="message my-message">Project has been already finished and I have results to show you.</div>
                            <div class="time">
                                <div class="message-data-time">10:10</div>
                            </div>
                        </li>
                        <li class="clearfix">
                            <div class="message other-message float-right"> Hi Aiden, how are you? How is the project coming along?</div>
                            <div class="time float-right">
                                <div class="message-data-time">10:10</div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="form-send">
                    <input type="text" class="form-control size-input" id="message">
                    <button class="form-control size-button" onclick="sendText()">Gửi</button>
                </div>
                --></div>
        </div>
        <script src="<c:url value="./static/js/chatbox.js"/>"></script>
    </body>
</html>