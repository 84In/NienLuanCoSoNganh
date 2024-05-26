/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.daos.impl;

import com.chatweb.daos.FriendDaoInterface;
import com.chatweb.mappers.impl.FriendMapper;
import com.chatweb.models.Friend;
import java.util.List;

/**
 *
 * @author ACER
 */
public class FriendDao extends GenericDao<Friend> implements FriendDaoInterface{
    
    private static FriendDao instance = null;
    
    public FriendDao(){
        
    }
    
    public synchronized static FriendDao getInstance(){
        if(instance == null){
            instance = new FriendDao();
        }
        return instance;
    }
    
    @Override
    public void saveFriend(String usernam1, String username2) {
        String sql = "insert into friends(user1,user2,status) values(?,?,?)";
        save(sql, usernam1, username2, 1);
    }

    @Override
    public void updateFriendByUsername(String username1, String username2, int status) {
        String sql = "UPDATE FRIENDS SET STATUS=? WHERE (USER1=? && USER2=?)||(USER2=? &&USER1=?);";
        save(sql, status, username1, username2, username1, username2);
    }

    @Override
    public void updateFriendByFriend(Friend friend, int status) {
        String sql ="UPDATE FRIENDS SET STATUS=? WHERE (USER1=? && USER2=?)";
        save(sql,status, friend.getUsername1(), friend.getUsername2());
    }

    @Override
    public int findStatusByUsername1AndUsername2(String username1, String username2) {
        String sql = "select user1, user2, status from friends where (USER1=? && USER2=?)||(USER2=? &&USER1=?);";
        List<Friend> friends = query(sql, new FriendMapper(), username1, username2, username1, username2);
        return friends != null? friends.get(0).getStatus() : 0;
    }
    
}
