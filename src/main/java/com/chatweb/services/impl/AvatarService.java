/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.services.impl;

import com.chatweb.daos.AvatarDaoInterface;
import com.chatweb.daos.impl.AvatarDao;
import com.chatweb.services.AvatarServiceInterface;

/**
 *
 * @author ACER
 */
public class AvatarService implements AvatarServiceInterface {

    private static AvatarService instance = null;

    AvatarDaoInterface avatarDaoInterface = AvatarDao.getInstance();

    public synchronized static AvatarService getInstance() {
        if (instance == null) {
            instance = new AvatarService();
        }
        return instance;
    }

    @Override
    public byte[] getImage(String username) {
        byte[] imageData = avatarDaoInterface.getImage(username);
        return imageData;
    }

}
