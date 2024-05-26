/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.mappers.impl;

import com.chatweb.mappers.RowMappersInterface;
import com.chatweb.models.BoxChat;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class IntForOne implements RowMappersInterface<Integer> {

    @Override
    public Integer mapRow(ResultSet rs) {
        try {
            Integer integer = rs.getInt(1);
            return integer;
        } catch (SQLException e) {
            return null;
        }
    }
    
}
