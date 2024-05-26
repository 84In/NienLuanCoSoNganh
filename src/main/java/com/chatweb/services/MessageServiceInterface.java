/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatweb.services;

import com.chatweb.models.dtos.MessageDTO;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface MessageServiceInterface {

    List<MessageDTO> getAllMessagesBySenderAndReceiver(String sender, String receiver);

    void saveMessage(MessageDTO messageDTO);
    
    void updateStatus(String sender, String receiver);
    
    int countMessage(String username, Long box_id);
    
    
}
