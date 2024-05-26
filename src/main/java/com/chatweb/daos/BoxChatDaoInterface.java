/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatweb.daos;

import com.chatweb.models.BoxChat;

/**
 *
 * @author ACER
 */
public interface BoxChatDaoInterface {
    void saveBox(BoxChat box);
    BoxChat findIdBoxBySenderAndReceiver(String sender, String receiver);
    BoxChat findIdBoxBySenderAndReceiverOfBoxChat(String sender, String receiver);
}
