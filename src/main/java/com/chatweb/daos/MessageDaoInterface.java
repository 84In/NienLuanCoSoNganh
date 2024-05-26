/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatweb.daos;

import com.chatweb.models.Message;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface MessageDaoInterface {

    List<Message> findAllMessagesBySenderAndReceiver(String sender, String receiver);

    void saveMessage(Message message);

    List<Message> findAllMessagesByConvesationId(Long id);
    
    void setSeen(String sender, String receiver);
    
    int getQuantity(String username, Long box_id);
}
