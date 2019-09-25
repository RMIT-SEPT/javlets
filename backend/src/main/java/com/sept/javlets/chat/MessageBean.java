package com.sept.javlets.chat;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sept.javlets.userauth.AccountBean;
import com.sept.javlets.userauth.StudentAccountBean;

@Document(collection="messages")
public class MessageBean {

	@Id
	private String id;
	
	private String messageContent;
	private AccountBean sender;
	private AccountBean recipient;
	private LocalDateTime date;
    
	public MessageBean(String messageContent, AccountBean author, AccountBean recipient) {
		this.date = LocalDateTime.now();
		this.messageContent = messageContent;
		this.sender = author;
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

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public AccountBean getSender() {
		return sender;
	}

	public void setSender(StudentAccountBean sender) {
		this.sender = sender;
	}

	public AccountBean getRecipient() {
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
