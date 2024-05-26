/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.services.impl;

import com.chatweb.daos.UserDaoInterface;
import com.chatweb.daos.impl.UserDao;
import com.chatweb.models.User;
import com.chatweb.models.dtos.UserDTO;
import com.chatweb.services.UserServiceInterface;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class UserService implements UserServiceInterface {

    private static UserService instance = null;

    private UserDaoInterface userDaoInterface = UserDao.getInstance();

    public synchronized static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public void saveUser(String username, String password, String name, InputStream inputStream) {
        User user = new User(username, password, name);
        userDaoInterface.saveUser(user, inputStream);
    }

    @Override
    public User findUserLogin(String username, String password) {
        User user = userDaoInterface.findUser(username, password);
        return user;
    }

    @Override
    public List<User> findFriendsByUsername(String username) {
        List<User> friends = userDaoInterface.findFriendsByName(username);
        return friends;
    }

    @Override
    public void updateStatus(User user, boolean status) {
        userDaoInterface.updateStatus(user, status);
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userDaoInterface.findUserByUsername(username);
        return user;
    }

    @Override
    public List<User> findFriendsByName(String name) {
        List<User> friends = userDaoInterface.findFriendsByName(name);
        return friends;
    }

    @Override
    public List<UserDTO> getListFriendsContactByUsername(String username) {
        List<User> friendsEnity = userDaoInterface.getListFriendsContactByUsername(username);
         List<UserDTO> listFriendsDTOs = new ArrayList<UserDTO>();
        friendsEnity.stream().forEach(msg -> {
            UserDTO UserDTO = convertToDTO(msg);
            listFriendsDTOs.add(UserDTO);
        });
        return listFriendsDTOs;
    }

    @Override
    public List<UserDTO> findUserDontAcceptByUsername(String username) {
        List<User> friendsEnity = userDaoInterface.findUserDontAcceptByUsername(username);
        
        List<UserDTO> listFriendsDTOs = new ArrayList<UserDTO>();
        friendsEnity.stream().forEach(msg -> {
            UserDTO UserDTO = convertToDTO(msg);
            listFriendsDTOs.add(UserDTO);
        });
        
        return listFriendsDTOs;
    }

    @Override
    public List<UserDTO> findFriendsByUsernameOrName(String text, String username) {
        List<User> friendsEnity = userDaoInterface.findFriendsByUsernameOrName(text, username);
        List<UserDTO> listFriendsDTOs = new ArrayList<UserDTO>();
        friendsEnity.stream().forEach(msg -> {
            
            UserDTO UserDTO = convertToDTO(msg);
            listFriendsDTOs.add(UserDTO);
        });
        return listFriendsDTOs;
    }

    private UserDTO convertToDTO(User userEntity) {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        String name = userEntity.getName();
        boolean online = userEntity.isOnline();
        UserDTO UserDTO = new UserDTO(username, password, name, online);
        return UserDTO;
    }

}
