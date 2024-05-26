/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.mappers.impl;

import com.chatweb.mappers.RowMappersInterface;
import com.chatweb.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class UserMapper implements RowMappersInterface<User> {

    @Override
    public User mapRow(ResultSet rs) {
        try {
            User user = new User();
            user.setUsername(rs.getString("username").trim());
            user.setPassword(rs.getString("password").trim());
            user.setName(rs.getString("name").trim());
            try {
                user.setOnline(rs.getBoolean("isOnline"));
            } catch (SQLException ex) {
                return user;
            }
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

}
