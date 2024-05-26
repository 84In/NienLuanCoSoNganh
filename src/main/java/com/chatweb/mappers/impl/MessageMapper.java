/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.mappers.impl;

import com.chatweb.mappers.RowMappersInterface;
import com.chatweb.models.Message;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class MessageMapper implements RowMappersInterface<Message>{
        @Override
	public Message mapRow(ResultSet rs) {
		try {
			Message message = new Message();
			message.setSender(rs.getString("sender").trim());
                        if (rs.getString("receiver") != null) {
				message.setReceiver(rs.getString("receiver").trim());
			}
			message.setContent(rs.getString("content"));
			message.setCreated_at(rs.getDate("created_at"));
			
			return message;
		} catch (SQLException e) {
			return null;
		}
	}
}
