/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatweb.services;

import com.chatweb.models.User;
import com.chatweb.models.dtos.UserDTO;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface UserServiceInterface {

    void saveUser(String username, String password, String name, InputStream inputStream);

    User findUserLogin(String username, String password);

    List<User> findFriendsByUsername(String username);
    
    List<User> findFriendsByName (String name);
    
    List<UserDTO> getListFriendsContactByUsername(String username);
    
    List<UserDTO> findFriendsByUsernameOrName(String text, String username);
    
    User findUserByUsername(String username);
    
    List<UserDTO> findUserDontAcceptByUsername(String username);
    
    void updateStatus(User user, boolean status);
}
