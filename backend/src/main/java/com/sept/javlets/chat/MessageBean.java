package com.sept.javlets.chat;

import java.time.LocalDateTime;

import com.sept.javlets.userauth.StudentAccountBean;

public class MessageBean {

	private String messageContent;
	private StudentAccountBean sender;
	private StudentAccountBean recipient;
	private LocalDateTime date;
	
	//TODO: Implement
//	private MessageType type;
	
//    public enum MessageType {
//        CHAT,
//        JOIN,
//        LEAVE
//    }
    
	public MessageBean(String messageContent, StudentAccountBean sender, StudentAccountBean recipient) {
		this.date = LocalDateTime.now();
		this.messageContent = messageContent;
		this.sender = sender;
		this.recipient = recipient;
	}
	
	public MessageBean() {
		super();
	}
	
	public MessageBean(String messageContent, StudentAccountBean sender) {
		this.date = LocalDateTime.now();
		this.messageContent = messageContent;
		this.sender = sender;
	}
	
	//TODO: Implement
//	public MessageType getType() {
//		return type;
//	}
//
//	public void setType(MessageType type) {
//		this.type = type;
//	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public StudentAccountBean getSender() {
		return sender;
	}

	public void setSender(StudentAccountBean sender) {
		this.sender = sender;
	}

	public StudentAccountBean getRecipient() {
		return recipient;
	}

	public void setRecipient(StudentAccountBean recipient) {
		this.recipient = recipient;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
