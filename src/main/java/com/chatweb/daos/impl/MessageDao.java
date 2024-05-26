/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.daos.impl;

import com.chatweb.daos.MessageDaoInterface;
import com.chatweb.mappers.impl.IntForOne;
import com.chatweb.mappers.impl.MessageMapper;
import com.chatweb.models.BoxChat;
import com.chatweb.models.Message;
import java.util.List;

/**
 *
 * @author ACER
 */
public class MessageDao extends GenericDao<Message> implements MessageDaoInterface {

    private static MessageDao instance = null;

    private MessageDao() {

    }

    public synchronized static MessageDao getInstance() {
        if (instance == null) {
            instance = new MessageDao();
        }
        return instance;
    }

    @Override
    public void saveMessage(Message message) {
        StringBuilder sql = new StringBuilder();
        String sender = message.getSender();
        String receiver = message.getReceiver();
        String content = message.getContent();
        boolean status = message.isStatus();
        Long box_id = message.getBox_id();
        if (box_id == null) {
            BoxChatDao boxChatDao = new BoxChatDao();
            BoxChat box = new BoxChat(sender + receiver);
            boxChatDao.saveBox(box);
            BoxChat newBox = boxChatDao.findIdBoxBySenderAndReceiver(sender, receiver);
            box_id = newBox.getId();
        }
        sql.append("insert into messages (sender, receiver, content, box_id, status) ");
        sql.append("values(?,?,?,?,?)");
        save(sql.toString(), sender, receiver, content, box_id, status);
    }

    @Override
    public List<Message> findAllMessagesByConvesationId(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select sender, receiver, content, created_at from messages");
        sql.append(" where box_id = ?");
        sql.append(" order by created_at asc");
        List<Message> listMessages = query(sql.toString(), new MessageMapper(), id);
        return listMessages;

    }

    @Override
    public List<Message> findAllMessagesBySenderAndReceiver(String sender, String receiver) {
        StringBuilder sql = new StringBuilder("select sender, receiver, content, created_at from messages ");
        sql.append("where (box_id = (select distinct bc.id from messages ms ");
        sql.append("join boxchat bc on bc.id = ms.box_id ");
        sql.append("where (ms.receiver=? && ms.sender=?)||(ms.receiver=? && ms.sender=?) ))");
        List<Message> listMessages = query(sql.toString(), new MessageMapper(), receiver, sender, sender, receiver);
        return listMessages;
    }
//select sender, receiver, content, created_at from message 
//	where (box_id = (select distinct bc.id from message ms
//						join boxchat bc on bc.id = ms.box_id
//								where (ms.receiver="B2110979" && ms.sender="admin")||(ms.receiver="admin" && ms.sender="B21100979")
//                                ));

    @Override
    public int getQuantity(String username, Long box_id) {
        String sql = "select count(*) from messages where sender=? and box_id=? and status = 1;";
        Integer quantity = queryForInt(sql, new IntForOne(), username, box_id);
        return quantity;
    }

    @Override
    public void setSeen(String sender, String receiver) {
        String sql = "update messages set status = 0 where ((sender=? and receiver=?) or (receiver=? and sender=?) ) and receiver=? and status = 1;";
        save(sql, sender, receiver, sender, receiver, sender);
    }

}
