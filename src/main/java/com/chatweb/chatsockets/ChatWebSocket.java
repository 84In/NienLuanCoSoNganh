/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.chatsockets;

import com.chatweb.models.MessageEncoder;
import com.chatweb.models.MessageDecode;
import com.chatweb.models.User;
import com.chatweb.models.dtos.MessageDTO;
import com.chatweb.services.ChatServiceAbstract;
import com.chatweb.services.MessageServiceInterface;
import com.chatweb.services.UserServiceInterface;
import com.chatweb.services.impl.BoxChatService;
import com.chatweb.services.impl.ChatService;
import com.chatweb.services.impl.MessageService;
import com.chatweb.services.impl.UserService;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

/**
 *
 * @author ACER
 */
@ServerEndpoint(value = "/chatbox/{username}", encoders = MessageEncoder.class, decoders = MessageDecode.class)
public class ChatWebSocket {

    private Session session;
    private String username;
    private ChatServiceAbstract chatService = ChatService.getInstance();
    private BoxChatService boxChatService = BoxChatService.getInstance();
    private MessageServiceInterface messageService = MessageService.getInstance();
    private UserServiceInterface userService = UserService.getInstance();

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        if (chatService.register(this)) {
            this.session = session;
            this.username = username;
            String receiver = "all";
            MessageDTO msgResponse = new MessageDTO(this.username, receiver, "[P]open", null);
            chatService.sendMessageToAllUsers(msgResponse);
            User user = userService.findUserByUsername(this.username);
            if (!user.isOnline()) {
                userService.updateStatus(user, true);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("com.chatweb.chatsockets.ChatWebSocket.onError() " + throwable);
    }

    @OnMessage
    public void onMessage(MessageDTO message, Session session) {
        String sender = message.getSender();
        String receiver = message.getReceiver();
        String senderReceiver = sender.concat(receiver);
        Long idBox = boxChatService.findIdBySenderAndReceiver(sender, receiver);
        if (idBox == 0) {
            boxChatService.saveBoxChat(senderReceiver);
            idBox = boxChatService.findIdBySenderAndReceiverOfBoxChat(message.getSender(), message.getReceiver());
        }
        message.setBox_id(idBox);
        chatService.sendMessageToOneUser(message);
        messageService.saveMessage(message);

    }

    @OnClose
    public void onClose(Session session) {
        if (chatService.close(this)) {
            String receiver = "all";
            MessageDTO msgResponse = new MessageDTO(this.username, receiver, "[P]close", null);
            chatService.sendMessageToAllUsers(msgResponse);
//            List<User> user = userService.findFriends(this.username);
//            userService.updateStatus(user.get(0), false);
            User user = userService.findUserByUsername(this.username);
            userService.updateStatus(user, false);
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
