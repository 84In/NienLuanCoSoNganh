/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.models;

import com.chatweb.models.dtos.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

/**
 *
 * @author ACER
 */
public final class MessageEncoder implements Encoder.Text<MessageDTO> {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String encode(final MessageDTO message) throws EncodeException {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new EncodeException(message, "Unable to encode message", e);
        }
    }

    @Override
    public void init(final EndpointConfig arg0) {
    }

    @Override
    public void destroy() {
    }

}
