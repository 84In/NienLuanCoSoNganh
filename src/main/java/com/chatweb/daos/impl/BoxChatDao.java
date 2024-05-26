/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.daos.impl;

import com.chatweb.daos.BoxChatDaoInterface;
import com.chatweb.mappers.impl.BoxChatMapper;
import com.chatweb.models.BoxChat;
import java.util.List;

/**
 *
 * @author ACER
 */
public class BoxChatDao extends GenericDao<BoxChat> implements BoxChatDaoInterface {

    private static BoxChatDao instance = null;

    public BoxChatDao() {

    }

    public synchronized static BoxChatDao getInstance() {
        if (instance == null) {
            instance = new BoxChatDao();
        }
        return instance;
    }

    @Override
    public void saveBox(BoxChat box) {
        String name = box.getName();
        String sql = "insert IGNORE into boxchat (name) values(?)";
        save(sql, name);
    }

    @Override
    public BoxChat findIdBoxBySenderAndReceiver(String sender, String receiver) {
        String sql = "select distinct bc.id, bc.name from boxchat bc "
                + "join messages ms on ms.box_id = bc.id "
                + "where ((ms.receiver=? && ms.sender=?)||(ms.receiver=? && ms.sender=?))";
        List<BoxChat> boxs = query(sql, new BoxChatMapper(), receiver, sender, sender, receiver);
        return boxs.isEmpty()? null : boxs.get(0);
    }

    @Override
    public BoxChat findIdBoxBySenderAndReceiverOfBoxChat(String sender, String receiver) {
        String sql = "select id, name from boxchat where name=? || name=?";
        String sr = sender.concat(receiver);
        String rs = receiver.concat(sender);
        List<BoxChat> box = query(sql, new BoxChatMapper(), sr, rs);
        return box.isEmpty() ? null : box.get(0);
    }

}
