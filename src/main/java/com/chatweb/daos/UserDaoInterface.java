/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatweb.daos;

import com.chatweb.models.User;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface UserDaoInterface {

    /**
     *
     * @param user
     */
    void saveUser(User user,InputStream inputStream);

    User findUser(String username, String password);

    List<User> findFriendsByUsername(String username);

    User findUserByUsername(String username);

    List<User> findFriendsByName(String name);

    List<User> getListFriendsContactByUsername(String username);

    List<User> findUserDontAcceptByUsername(String username);

    List<User> findFriendsByUsernameOrName(String text, String username);

    void updateStatus(User user, boolean status);
}
