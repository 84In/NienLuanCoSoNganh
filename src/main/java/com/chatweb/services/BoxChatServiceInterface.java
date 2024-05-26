/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatweb.services;

import com.chatweb.models.BoxChat;

/**
 *
 * @author ACER
 */
public interface BoxChatServiceInterface {
    Long findIdBySenderAndReceiver(String sender, String receiver);
    Long findIdBySenderAndReceiverOfBoxChat(String sender, String receiver);
    void saveBoxChat(String name);
}
