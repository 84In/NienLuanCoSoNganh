/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.daos.impl;

import com.chatweb.daos.UserDaoInterface;
import com.chatweb.mappers.impl.*;

import com.chatweb.models.User;
import com.chatweb.security.AES256;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ACER
 */
public class UserDao extends GenericDao<User> implements UserDaoInterface {

    private String secretKey = "123@ABC";
    private String salt = "chatbox";

    private static UserDao instance = null;

    public UserDao() {

    }

    public synchronized static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    @Override
    public void saveUser(User user, InputStream inputStream) {
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getName();
        String sql = "insert into users(username, password, name, avatar, isOnline) values(?,?,?,?,?);";
        save(sql, username, password, name, inputStream, false);
        System.out.println("com.chatweb.daos.impl.UserDao.saveUser() " + inputStream.toString());
    }

    @Override
    public User findUser(String username, String password) {
        String sql = new String("select username, name, password from users where username=?");
        List<User> users = query(sql.toString(), new UserMapper(), username);
        users.forEach((user) -> {
            user.setPassword(AES256.decrypt(user.getPassword(), secretKey, salt));
        });
        List<User> kq = users.stream().filter(u -> u.getPassword().equals(password)).collect(Collectors.toList());
        return kq.isEmpty() ? null : kq.get(0);

    }

    @Override
    public List<User> findFriendsByUsername(String username) {
        String sql = new String("select username, name, password, isOnline from users where username = ?");
        List<User> users = query(sql.toString(), new UserMapper(), username);
        return users.stream().filter(u -> !u.getUsername().equals(username)).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(User user, boolean status) {
        String sql = "UPDATE users SET isOnline=? WHERE username=?";
        String username = user.getUsername();
        save(sql, status, username);
    }

    @Override
    public User findUserByUsername(String username) {
        String sql = new String("select username, name, password, isOnline from users where username = ?");
        List<User> users = query(sql.toString(), new UserMapper(), username);
        return users != null ? users.get(0) : null;
    }

    @Override
    public List<User> findFriendsByName(String name) {
        String sql = new String("select username, name, password, isOnline from users where name != ?");
        List<User> users = query(sql.toString(), new UserMapper(), name);
        return users != null ? users : null;
    }

    @Override
    public List<User> getListFriendsContactByUsername(String username) {
        StringBuilder sql = new StringBuilder("select username, password, name, isonline from users where username in");
        sql.append(" (select user1 as username from friends where user2 = ? and status = 2 union");
        sql.append(" select user2 as username from friends where user1 = ? and status = 2);");
        List<User> users = query(sql.toString(), new UserMapper(), username, username);
        return users != null ? users : null;
    }

    @Override
    public List<User> findUserDontAcceptByUsername(String username) {
        StringBuilder sql = new StringBuilder("select username, password, name, isonline from users where username in ");
        sql.append("((select user1 as  username from friends where (user2=? and status = 1)) union (select user2 as username from friends where (user1 =? and status=1)));");
        List<User> users = query(sql.toString(), new UserMapper(), username, username);
        return users != null ? users : null;
    }

    @Override
    public List<User> findFriendsByUsernameOrName(String text, String username) {
        StringBuilder sql = new StringBuilder("select username, password, name, isonline from users where (username =? or name =? or name like ? or name like ? or name like ?) and ");
        sql.append("username not in(select user1 as  username from friends where user2=? union select user2 as username from friends where user1 =?);");
        List<User> users = query(sql.toString(), new UserMapper(), text, text, "%"+text+"%", text+"%", "%"+text, username, username);
        return users != null ? users : null;
    }
}
