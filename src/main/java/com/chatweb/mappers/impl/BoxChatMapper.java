/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.mappers.impl;

import com.chatweb.mappers.RowMappersInterface;
import com.chatweb.models.BoxChat;
import com.chatweb.models.Message;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class BoxChatMapper implements RowMappersInterface<BoxChat> {

    @Override
    public BoxChat mapRow(ResultSet rs) {
        try {
            BoxChat box = new BoxChat();
            box.setId(rs.getLong("id"));
            box.setName(rs.getString("name").trim());
            return box;
        } catch (SQLException e) {
            return null;
        }

    }

}
