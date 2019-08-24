package com.sept.javlets.chat;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sept.javlets.userauth.StudentAccountBean;

public class MessageBean {

	private String messageContent;
	private StudentAccountBean sender;
	private String sendersName;
	private StudentAccountBean recipient;
	private LocalDateTime date;
	private MessageType type;
	
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
	public MessageBean(String messageContent, StudentAccountBean sender, StudentAccountBean recipient) {
		this.date = LocalDateTime.now();
		this.messageContent = messageContent;
		this.sender = sender;
		this.recipient = recipient;
	}
	
	public MessageBean() {
		super();
	}
	
	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

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
	
	public void setSender(String sender) {
		this.sendersName = sender;
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
