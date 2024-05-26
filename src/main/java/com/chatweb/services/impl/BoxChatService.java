/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatweb.services.impl;

import com.chatweb.daos.BoxChatDaoInterface;
import com.chatweb.daos.impl.BoxChatDao;
import com.chatweb.models.BoxChat;
import com.chatweb.services.BoxChatServiceInterface;

/**
 *
 * @author ACER
 */
public class BoxChatService implements BoxChatServiceInterface{
     private static BoxChatService instance = null;

	private BoxChatDaoInterface boxChatDaoInterface = BoxChatDao.getInstance();
        
        public BoxChatService(){
            
        }

	public synchronized static BoxChatService getInstance() {
		if (instance == null) {
			instance = new BoxChatService();
		}
		return instance;
	}

    @Override
    public Long findIdBySenderAndReceiver(String sender, String receiver) {
        BoxChat box = boxChatDaoInterface.findIdBoxBySenderAndReceiver(sender, receiver);
        return box == null ? 0 : box.getId() ;
    }


    @Override
    public Long findIdBySenderAndReceiverOfBoxChat(String sender, String receiver) {
        BoxChat boxs = boxChatDaoInterface.findIdBoxBySenderAndReceiverOfBoxChat(sender, receiver);
        return boxs.getId();
    }

    @Override
    public void saveBoxChat(String name) {
        BoxChat boxEntity = new BoxChat(name);
        boxChatDaoInterface.saveBox(boxEntity);
        
    }
    
}
