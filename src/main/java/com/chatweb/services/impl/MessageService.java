/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.services.impl;

import com.chatweb.daos.MessageDaoInterface;
import com.chatweb.daos.impl.MessageDao;
import com.chatweb.models.Message;
import com.chatweb.models.dtos.MessageDTO;
import com.chatweb.services.MessageServiceInterface;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class MessageService implements MessageServiceInterface{
    private static MessageService instance = null;

	private MessageDaoInterface messageDaoInterface = MessageDao.getInstance();

	public synchronized static MessageService getInstance() {
		if (instance == null) {
			instance = new MessageService();
		}
		return instance;
	}

	private Message convertToEntity(MessageDTO messageDTO) {
		String sender = messageDTO.getSender();
		String content = messageDTO.getContent();
		String receiver = messageDTO.getReceiver();
		Long box_id = messageDTO.getBox_id();
		Message messageEntity = new Message(sender, receiver, content, box_id);
		return messageEntity;
	}

	private MessageDTO convertToDTO(Message messageEntity) {
		String sender = messageEntity.getSender();
		String content = messageEntity.getContent();
		String receiver = messageEntity.getReceiver();
                Date created_at = messageEntity.getCreated_at();
		Long box_id = messageEntity.getBox_id();
		MessageDTO messageDTO = new MessageDTO( sender, receiver, content, box_id);
                messageDTO.setCreated_at(created_at);
		return messageDTO;
	}

	@Override
	public List<MessageDTO> getAllMessagesBySenderAndReceiver(String sender, String receiver) {
		List<Message> listMessages = messageDaoInterface.findAllMessagesBySenderAndReceiver(sender, receiver);
                messageDaoInterface.setSeen(sender, receiver);
		List<MessageDTO> listMessageDTOs = new ArrayList<MessageDTO>();
		listMessages.stream().forEach(msg -> {
			MessageDTO messageDTO = convertToDTO(msg);
			listMessageDTOs.add(messageDTO);
		});
		return listMessageDTOs;
	}

	@Override
	public void saveMessage(MessageDTO messageDTO) {
		Message messageEntity = convertToEntity(messageDTO);
                messageEntity.setStatus(true);
		messageDaoInterface.saveMessage(messageEntity);
	}

    @Override
    public int countMessage(String username, Long box_id) {
        int quantity = messageDaoInterface.getQuantity(username, box_id);
        return quantity;
    }

    @Override
    public void updateStatus(String sender, String receiver) {
        messageDaoInterface.setSeen(sender, receiver);
    }
        

}
