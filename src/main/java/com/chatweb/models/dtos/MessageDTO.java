/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ACER
 */
public class MessageDTO {

    @JsonbProperty("sender")
    private String sender;
    @JsonbProperty("receiver")
    private String receiver;
    @JsonbProperty("content")
    private String content;
    @JsonbProperty("created_at")
    private Date created_at;
    @JsonbProperty("box_id")
    private Long box_id;

    @JsonProperty("onlineList")
    private Set<String> onlineList = new HashSet<String>();

    public MessageDTO() {
    }

    @JsonbCreator
    public MessageDTO(
            @JsonbProperty("sender") String sender,
            @JsonbProperty("receiver") String receiver,
            @JsonbProperty("content") String content,
            @JsonbProperty("box_id") Long box_id
    ) {

        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.box_id = box_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Long getBox_id() {
        return box_id;
    }

    public void setBox_id(Long box_id) {
        this.box_id = box_id;
    }

    public Set<String> getOnlineList() {
        return onlineList;
    }

    public void setOnlineList(Set<String> onlineList) {
        this.onlineList = onlineList;
    }

}
