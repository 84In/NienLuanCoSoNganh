/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.services.impl;

import com.chatweb.daos.FriendDaoInterface;
import com.chatweb.daos.impl.FriendDao;
import com.chatweb.models.Friend;
import com.chatweb.services.FriendServiceInterface;

/**
 *
 * @author ACER
 */
public class FriendService implements FriendServiceInterface{

    
    private static FriendService instance = null;

    private FriendDaoInterface friendDaoInterface = FriendDao.getInstance();

    public synchronized static FriendService getInstance() {
        if (instance == null) {
            instance = new FriendService();
        }
        return instance;
    }

    @Override
    public void saveFriend(String usernam1, String username2) {
        friendDaoInterface.saveFriend(usernam1, username2);
    }

    @Override
    public void updateFriendByUsername(String username1, String username2, int status) {
        friendDaoInterface.updateFriendByUsername(username1, username2, status);
    }

    @Override
    public void updateFriendByFriend(Friend friend, int status) {
        friendDaoInterface.updateFriendByFriend(friend, status);
    }

    @Override
    public int findStatusByUsername1AndUsername2(String username1, String username2) {
         return friendDaoInterface.findStatusByUsername1AndUsername2(username1, username2);
    }
    
    
    
}
