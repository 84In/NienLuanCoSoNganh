/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/gulpfile.js to edit this template
 */

var username = null;
var websocket = null;
var receiver = null;
var userAvatar = null;
var receiverAvatar = null;

var groupId = null;
var groupName = null;

var temp = null;

window.onload = function () {
    if ("WebSocket" in window) {
        username = document.getElementById("username").textContent;
        console.log(username);
        //        userAvatar = document.getElementById("userAvatar").textContent;
        websocket = new WebSocket(
                "ws://" + window.location.host + "/ChatWeb/chatbox/" + username
                );

        websocket.onopen = function () {
            console.log("Da noi ket");
        };

        websocket.onmessage = function (data) {
            setMessage(JSON.parse(data.data));
        };

        websocket.onerror = function () {
            console.log("An error occured, closing application");
            cleanUp();
        };

        websocket.onclose = function (data) {
            console.log(data.reason);
            cleanUp();
        };
    } else {
        console.log("Websockets not supported");
    }
};

function cleanUp() {
    username = null;
    websocket = null;
    receiver = null;
}

function closeChat() {
    console.log("Bat daun  xoa chat");
    document.getElementById("receiver").innerHTML = " ";
    removeClassActive(temp);
    console.log("xoa chat");
}
function setReceiver(element) {
    groupId = null;
    receiver = element.id;
    receiverName = document.getElementById("name-" + receiver).textContent;
    console.log('name-' + receiver);
    receiverAvatar = document.getElementById("img-" + receiver).src;
    var status = "offline";
    if (
            document.getElementById("status-" + receiver).classList.contains("online")
            ) {
        status = "online";
    }
    var rightSide =
            '<div class="chat-header clearfix">' +
            '<div class="header-right">' +
            '<img src="' + receiverAvatar + '" alt="avatar user">' +
            "<h1>" +
            receiverName +
            "</h1>" +
            '<div class="status ' +
            status +
            '">' +
            (status == "online" ? "Đang hoạt động" : "Không hoạt động") +
            "</div>" +
            '<div class="close" onclick="closeChat()">' +
            '<i class="fa-regular fa-circle-xmark" ></i>' +
            "</div>" +
            "</div>" +
            "</div>" +
            '<div class="chat-history">' +
            '<ul class="m-b-0" id="chat">' +
            "</ul>" +
            "</div>" +
            '<div class="form-send">' +
            '<input type="text" class="form-control size-input" id="message">' +
            '<button class="form-control size-button" onclick="sendText()">Gửi</button>' +
            "</div>";

    if (temp != null) {
        removeClassActive(temp);
    }

    temp = document.getElementById(receiver);

    temp.classList.add("active");

    document.getElementById("receiver").innerHTML = rightSide;

    loadMessage();
}

function removeClassActive(element) {
    //    var active = document.getElementsByClassName("active");
    //    for( i = 0; i < active.length; i++){
    //        active[i].classList.remove("active");
    //    }
    element.classList.remove("active");
}

function loadMessage() {
    console.log("load message ...");
    var currentChatbox = document.getElementById("chat");
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                var messages = JSON.parse(this.responseText);
                var chatbox = "";
                messages.forEach((msg) => {
                    try {
                        chatbox += customLoadMessage(msg.sender, msg.content);
                        console.log(chatbox);
                    } catch (ex) {
                    }
                });
                currentChatbox.innerHTML = chatbox;
                goLastestMsg();
            } else {
                console.log("Loi status = " + this.status);
            }
        } else {
            console.log("Loi readState = " + this.readyState);
        }
    };
    xhttp.open(
            "GET",
            "http://" +
            window.window.location.host +
            "/ChatWeb/ChatRestController?sender=" +
            username +
            "&receiver=" +
            receiver,
            true
            );
    xhttp.send();
    console.log("Colection message");
}
function setMessage(msg) {
    console.log("online users: " + msg.onlineList);
    if (msg.content != "[P]open" && msg.content != "[P]close") {
        var currentChat = document.getElementById("chat").innerHTML;
        var newChatMsg = "";
        if (msg.receiver != null) {
            newChatMsg = customLoadMessage(msg.sender, msg.content);
        }
        document.getElementById("chat").innerHTML = currentChat + newChatMsg;
        goLastestMsg();
    } else {
        if (msg.content === "[P]open") {
            msg.onlineList.forEach((username) => {
                try {
                    setOnline(username, true);
                } catch (ex) {
                }
            });
        } else {
            setOnline(msg.username, false);
        }
    }
}

/*
 * 
 <li class="clearfix">
 <div class="message other-message float-right">s the project coming along?</div>
 <div class="time float-right">
 <div class="message-data-time">10:10</div>
 </div>
 </li>
 * 
 */

function customLoadMessage(sender, message) {
    console.log("Thong diep message: " + message);
    var msgDisplay = '<li class="clearfix">' + '<div class="message ';
    if (receiver != sender && username != sender) {
        return "";
    } else if (receiver == sender) {
        msgDisplay += 'my-message">' + message + "</div>";
    } else {
        msgDisplay += 'other-message float-right">' + message + "</div>";
    }
    //    var today = new Date();
    //    var time = today.getHours() + ":" + today.getMinutes();
    return (
            msgDisplay +
            '<div class="message-data-time">' +
            "</div>" +
            "</div>" +
            " </li>"
            );
}
function setOnline(username, isOnline) {
    let ele = document.getElementById("status-" + username);

    if (isOnline === true) {
        ele.classList.add("online");
    } else {
        ele.classList.remove("online");
    }
}
function sendMessage(e) {
    e.preventDefault();
    var inputText = document.getElementById("message").value;
    if (inputText != "") {
        sendText();
    }
    return false;
}
function sendText() {
    var messageContent = document.getElementById("message").value;
    console.log("Noi dung message: " + messageContent);
    document.getElementById("message").value = "";
    var message = buildMessageToJson(messageContent);
    console.log(message);

    setMessage(message);

    websocket.send(JSON.stringify(message));

    console.log("Giá trị message JSon: " + JSON.stringify(message));
}
function buildMessageToJson(message) {
    return {
        sender: username,
        receiver: receiver,
        content: message,
        //        created_at: "",
        box_id: Number(groupId),
    };
}
function goLastestMsg() {
    var msgLiTags = document.querySelectorAll(".message");
    var last = msgLiTags[msgLiTags.length - 1];
    try {
        last.scrollIntoView();
    } catch (ex) {
    }
}
function showTableAddFriends() {
    var rightSide =
            '<div class="addFriendTable">' +
            '<div class="table">' +
            '<div class="thead">' +
            "<h3>" +
            '<label for="friends">Thêm bạn bè</label>' +
            "</h3>" +
            '<input type="text" name="friends" id="searchFriend">' +
            '<button class="btnsearch" type="button" onclick="searchFriend()">Search</button>' +
            '<span class="close-friend" onclick="closeFriend()"><i class="fa-solid fa-x"></i></span>' +
            "</div>" +
            '<div id="resultFriends">' +
            "</div></div></div>";
    document.getElementById("receiver").innerHTML = rightSide;
}
function customLoadUser(name, username) {
    var temp = '<li class="tagname">' +
            '<span><img src="AvatarRestController?username=' + username + '" alt="" class="avatar-img"></span>' +
            '<span><strong class="decorname">' + name + '</strong></span>' +
            '<span><input id="btn-' + username + '" class="btn requestAddFriend"type="button"value="Thêm bạn bè"onclick="' +
            "addFriend('" + username + "')" +
            '"></span></li>';
    return temp;
}
function searchFriend() {
    // Lấy dữ liệu từ ô nhập
    var searchText = document.getElementById("searchFriend").value;

    // Xử lý dữ liệu tìm kiếm ở đây, ví dụ:
    loadUser(searchText);
}

// Thêm sự kiện xử lý khi người dùng nhấn Enter trong ô nhập
document.addEventListener("keypress", function (event) {
    // Kiểm tra xem phím được nhấn là Enter và phần tử tương ứng là "searchFriend"
    if (event.key === "Enter" && event.target.id === "searchFriend") {
        // Gọi hàm searchFriend() khi nhấn Enter trong phần tử "searchFriend"
        searchFriend();
    }
});
// Thêm sự kiện xử lý khi người dùng nhấn Enter trong ô nhập
document.addEventListener("keypress", function (event) {
    // Kiểm tra xem phím được nhấn là Enter và phần tử tương ứng là "searchFriend"
    if (event.key === "Enter" && event.target.id === "message") {
        // Gọi hàm searchFriend() khi nhấn Enter trong phần tử "searchFriend"
        sendText();
    }
});
function closeFriend() {
    console.log("Bat dau xoa list new friend");
    document.getElementById("receiver").innerHTML = " ";
    console.log("xoa list new friend");
}
function loadUser(textSearch) {
    console.log("load user ...");
    var currentResultFriends = document.getElementById("resultFriends");
    var xhttp = new XMLHttpRequest();
    let usernametemp = document.getElementById("username").textContent;
    xhttp.open(
            "GET",
            "http://" +
            window.window.location.host +
            "/ChatWeb/UserRestController?action=listuser" + "&text=" + textSearch + "&username=" + usernametemp,
            true
            );
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let myUsername = document.getElementById("username").textContent;
            var users = JSON.parse(this.responseText);
            var ResultFriends = '<ul class="listaddfriend">';
            users.forEach((msg) => {
                try {
                    if (msg.username != myUsername) {
                        ResultFriends += customLoadUser(msg.name, msg.username);
                        flag = 1;
                    }

                    console.log(ResultFriends);
                } catch (ex) {
                }
            });
            currentResultFriends.innerHTML = ResultFriends + "</ul>";
        } else {
//            document.getElementById("searchFriend").value = "";
//              currentResultFriends.innerHTML = "Không có nội dung tìm kiếm!!";

        }
    };

    console.log("Colection users");
}
function addFriend(index) {

    var currentBtnUser = document.getElementById("btn-" + index);
    var xhttp = new XMLHttpRequest();

    xhttp.open(
            "GET",
            "http://" +
            window.window.location.host +
            "/ChatWeb/FriendRestController?action=addfriend" + "&user1=" + this.username + "&user2=" + index,
            true
            );
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            var status = JSON.parse(this.responseText);
            if (status == true) {
                currentBtnUser.readonly;
                currentBtnUser.value = "Đã gửi lời mời kết bạn";
                currentBtnUser.style = "background-color: #444546";
            }
        } else {
            document.getElementById("searchFriend").value = "";

        }
    };

    console.log("Colection users");
}
function showTableAcceptFriends() {
    var rightSide =
            '<div class="addFriendTable">' +
            '<div class="table">' +
            '<div class="thead">' +
            "<h2 style='text-align: center'>Lời mời kết bạn</h2>" +
            "</div>" +
            '<div id="resultFriends">' +
            "</div></div></div>";
    document.getElementById("receiver").innerHTML = rightSide;
    loadUserAccept(document.getElementById("username").textContent);
}
function loadUserAccept(index) {
    console.log("load user ...");
    console.log(index);
    var currentResultFriends = document.getElementById("resultFriends");
    var xhttp = new XMLHttpRequest();
    xhttp.open(
            "GET",
            "http://" +
            window.window.location.host +
            "/ChatWeb/UserRestController?action=acceptfriends" + "&username=" + index,
            true
            );
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var users = JSON.parse(this.responseText);
            var ResultFriends = '<ul class="listaddfriend">';
            users.forEach((msg) => {
                try {
                    ResultFriends += customLoadUserAccept(msg.name, msg.username, index);
                    console.log(ResultFriends);
                } catch (ex) {
                    console.log(ex);
                }
            });
            currentResultFriends.innerHTML = ResultFriends + "</ul>";
        } else {

        }
    };

    console.log("Colection friends");
}
function customLoadUserAccept(name, username, index) {
    var temp = '<li class="tagname">' +
            '<span><img src="AvatarRestController?username=' + username + '" alt="" class="avatar-img"></span>' +
            '<span><strong class="decorname">' + name + '</strong></span>' +
            '<span><input id="btn-' + username + '" class="btn requestAddFriend"type="button"value="Đồng ý kết bạn"onclick="' +
            "AcceptFriend('" + username + "','" + index + "')" +
            '"></span></li>';
    return temp;
}
function AcceptFriend(user2, user1) {
    var currentBtnUser = document.getElementById("btn-" + user2);
    var xhttp = new XMLHttpRequest();

    xhttp.open(
            "GET",
            "http://" +
            window.window.location.host +
            "/ChatWeb/FriendRestController?action=acceptfriend" + "&user1=" + user2 + "&user2=" + user1,
            true
            );
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            var status = JSON.parse(this.responseText);
            if (status == true) {
                currentBtnUser.readonly;
                currentBtnUser.value = "Đã đồng ý kết bạn";
                currentBtnUser.style = "background-color: #444546";
            }
        } else {

        }
    };

    console.log("Colection users");
}
function loadListUserContactMe() {
    console.log("load user ...");
    let myUsername = document.getElementById("username").textContent;
    var currentResultFriends = document.getElementById("listfriendsonline");
    var xhttp = new XMLHttpRequest();
    xhttp.open(
            "GET",
            "http://" +
            window.window.location.host +
            "/ChatWeb/UserRestController?action=getfriends" + "&username=" + myUsername,
            true
            );
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var users = JSON.parse(this.responseText);
            var ResultFriends = '<ul>';
            users.forEach((msg) => {
                try {
                    ResultFriends += customLoadUserOnline(msg.name, msg.username, msg.online, msg.countMess);
                    console.log(ResultFriends);
                } catch (ex) {
                    console.log(ex);
                }
            });
            currentResultFriends.innerHTML = ResultFriends + "</ul>";

        } else {

        }
    };


}
loadListUserContactMe();
function customLoadUserOnline(name, username, isonline, countMess) {
    var temp = '<li>' +
            '<div class="box-friend" id="' + username + '" onclick="setReceiver(this);">' +
            '<img id="img-' + username + '" class="avatar-img" src="AvatarRestController?username=' + username + '" alt="avatar">'+
            '<h3 id="name-' + username + '" class="name-friend">' + name + '</h3>';
    if (countMess > 0)
        temp += '<span class="messageCount">' + countMess + '</span>';

    if (isonline == true) {
        temp += '<div id="status-' + username + '" class="status online">Đang hoạt động</div>';
    } else {
        temp += '<div id="status-' + username + '" class="status offline">Không hoạt động</div>'
    }
    temp += '</div></li>';
    return temp;
}
setInterval(loadListUserContactMe, 1000);