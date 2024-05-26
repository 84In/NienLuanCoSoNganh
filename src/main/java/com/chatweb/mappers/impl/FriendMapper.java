/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.mappers.impl;

import com.chatweb.mappers.RowMappersInterface;
import com.chatweb.models.Friend;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class FriendMapper implements RowMappersInterface<Friend>{

    @Override
    public Friend mapRow(ResultSet rs) {
        try {
            Friend friend = new Friend();
            friend.setUsername1(rs.getString("user1").trim());
            friend.setUsername2(rs.getString("user2").trim());
            friend.setStatus(rs.getInt("status"));
            return friend;
        } catch (SQLException e) {
            return null;
        }
    }
    
}
