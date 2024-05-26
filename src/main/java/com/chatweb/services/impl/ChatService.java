/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.services.impl;

import com.chatweb.chatsockets.ChatWebSocket;
import com.chatweb.daos.BoxChatDaoInterface;
import com.chatweb.daos.UserDaoInterface;
import com.chatweb.daos.impl.BoxChatDao;
import com.chatweb.daos.impl.UserDao;
import com.chatweb.models.dtos.MessageDTO;
import com.chatweb.services.ChatServiceAbstract;
import jakarta.websocket.EncodeException;
import java.io.IOException;

/**
 *
 * @author ACER
 */
public class ChatService extends ChatServiceAbstract {

    private static ChatService chatService = null;

    private ChatService() {

    }

    public synchronized static ChatService getInstance() {
        if (chatService == null) {
            chatService = new ChatService();
        }
        return chatService;
    }

    @Override
    public boolean register(ChatWebSocket chatWebsocket) {
        return chatWebsockets.add(chatWebsocket);
    }

    @Override
    public boolean close(ChatWebSocket chatWebsocket) {
        return chatWebsockets.remove(chatWebsocket);
    }

    @Override
    public void sendMessageToAllUsers(MessageDTO message) {
        message.setOnlineList(getUsernames());
        chatWebsockets.stream().forEach(chatWebsocket -> {
            try {
                chatWebsocket.getSession().getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void sendMessageToOneUser(MessageDTO message) {

        chatWebsockets.stream()
                .filter(chatWebsocket -> chatWebsocket.getUsername().equals(message.getReceiver()))
                .forEach(chatWebsocket -> {
                    System.out.println("com.chatweb.services.impl.ChatService.sendMessageToOneUser() username: "+chatWebsocket.getUsername()+" message Receiver: "+message.getReceiver());
                    try {
                        chatWebsocket.getSession().getBasicRemote().sendObject(message);
                    } catch (IOException | EncodeException e) {
                        e.printStackTrace();
                    }
                });

    }

    @Override
    public boolean isUserOnline(String username) {
        for (ChatWebSocket chatWebsocket : chatWebsockets) {
            if (chatWebsocket.getUsername().equals(username)) {
                System.out.println("com.chatweb.services.impl.ChatService.isUserOnline() username: "+chatWebsocket.getUsername()+" username: "+username);
                return true;
            }
        }
        return false;
    }
}
