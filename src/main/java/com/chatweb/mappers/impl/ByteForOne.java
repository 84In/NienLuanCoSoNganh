/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.mappers.impl;

import com.chatweb.mappers.RowMappersInterface;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class ByteForOne implements RowMappersInterface<byte[]>{

    @Override
    public byte[] mapRow(ResultSet rs) {
     
        try {
             Blob blob = rs.getBlob("avatar");
             byte[] imageData = blob.getBytes(1, (int) blob.length());
            return imageData;
        } catch (SQLException e) {
            return null;
        }
    }
    
}
