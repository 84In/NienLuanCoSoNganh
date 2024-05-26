/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatweb.services;

import com.chatweb.models.Friend;

/**
 *
 * @author ACER
 */
public interface FriendServiceInterface {

    void saveFriend(String usernam1, String username2);

    void updateFriendByUsername(String username1, String username2, int status);

    void updateFriendByFriend(Friend friend, int status);

    int findStatusByUsername1AndUsername2(String username1, String username2);
}
