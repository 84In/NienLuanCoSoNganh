/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.models;

import com.chatweb.models.dtos.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;
import java.io.IOException;

/**
 *
 * @author ACER
 */
public final class MessageDecode implements Decoder.Text<MessageDTO>{
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public MessageDTO decode(final String arg0) throws DecodeException {
        try {
            return objectMapper.readValue(arg0, MessageDTO.class);
        } catch (IOException e) {
            throw new DecodeException(arg0, "Unable to decode text to Message", e);
        }
    }

    @Override
    public boolean willDecode(final String arg0) {
        return arg0.contains("sender") && arg0.contains("content");
    }

    @Override
    public void init(final EndpointConfig arg0) {
        
    }

    @Override
    public void destroy() {
    }
    
}
