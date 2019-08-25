package com.sept.javlets.chat;

import java.util.ArrayList;
import java.util.List;

import com.sept.javlets.userauth.StudentAccountBean;

public class MessageList {

	private List<MessageBean> messages;
	
	public MessageList() {
		this.messages = new ArrayList<MessageBean>();

		messages.add(new MessageBean("Hey", new StudentAccountBean("John")));
		messages.add(new MessageBean("Hi", new StudentAccountBean("Josh")));
	}

	public void addMessage(MessageBean message) {
		messages.add(message);
	}
	
	public List<MessageBean> filterSender(StudentAccountBean sender) {
		List<MessageBean> fromSender = new ArrayList<MessageBean>();
		for (MessageBean message : messages) {
			if (message.getSender().equals(sender))
				fromSender.add(message);
		}
		
		return fromSender;
	}
	
	public List<MessageBean> filterRecipient(StudentAccountBean recipient) {
		List<MessageBean> toRecipient = new ArrayList<MessageBean>();
		for (MessageBean message : messages) {
			if (message.getRecipient().equals(recipient))
				toRecipient.add(message);
		}
		
		return toRecipient;
	}	  
	
	public List<MessageBean> getAllMessages() {
		System.out.println("Returning " + messages.size() + " messages");
		return messages;
	}
	
}
