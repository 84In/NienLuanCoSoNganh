/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.daos.impl;

import com.chatweb.daos.AvatarDaoInterface;
import com.chatweb.mappers.impl.ByteForOne;

/**
 *
 * @author ACER
 */
public class AvatarDao extends GenericDao<byte[]> implements AvatarDaoInterface {

    private static AvatarDao instance = null;

    public AvatarDao() {

    }

    public synchronized static AvatarDao getInstance() {
        if (instance == null) {
            instance = new AvatarDao();
        }
        return instance;
    }

    @Override
    public byte[] getImage(String username) {
        String sql = "SELECT avatar FROM users WHERE username =?";
        byte[] imageData = queryForByte(sql, new ByteForOne(), username);
        return imageData;
    }

}
