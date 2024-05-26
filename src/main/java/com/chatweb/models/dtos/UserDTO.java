/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.models.dtos;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

/**
 *
 * @author ACER
 */
public class UserDTO {
    @JsonbProperty("username")
    private String username;
    @JsonbProperty("password")
    private String password;
    @JsonbProperty("name")
    private String name;
    @JsonbProperty("online")
    private boolean online;
    @JsonbProperty("countMess")
    private int countMess;
    
    public UserDTO() {
    }

    @JsonbCreator()
    public UserDTO(@JsonbProperty("username") String username,
    @JsonbProperty("password") String password,
    @JsonbProperty("name") String name,
    @JsonbProperty("online") boolean online) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.online = online;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public int getCountMess() {
        return countMess;
    }

    public void setCountMess(int countMess) {
        this.countMess = countMess;
    }
    
}
