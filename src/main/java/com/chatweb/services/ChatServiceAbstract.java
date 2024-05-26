/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatweb.services;

import com.chatweb.chatsockets.ChatWebSocket;
import com.chatweb.models.dtos.MessageDTO;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @author ACER
 */
public abstract class ChatServiceAbstract {

    protected static final Set<ChatWebSocket> chatWebsockets = new CopyOnWriteArraySet<>();

    public abstract boolean register(ChatWebSocket chatWebsocket);

    public abstract boolean close(ChatWebSocket chatWebsocket);

    public abstract void sendMessageToAllUsers(MessageDTO message);

    public abstract void sendMessageToOneUser(MessageDTO message);


    public abstract boolean isUserOnline(String username);

    /**
     *
     * @return
     */
    protected Set<String> getUsernames() {
        Set<String> usernames = new HashSet<String>();
        chatWebsockets.forEach(chatWebsocket -> {
            usernames.add(chatWebsocket.getUsername());
        });
        return usernames;
    }
}
