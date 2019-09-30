package com.sept.javlets.chat;

import com.sept.javlets.userauth.AccountBean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "messages")
public class MessageBean {

    @Id
    private String id;

    private String messageContent;
    private AccountBean sender;
    private String senderId;
    private AccountBean recipient;
    private String recipientId;
    private LocalDateTime date;

    public MessageBean(String messageContent, AccountBean sender, AccountBean recipient) {
        this.date = LocalDateTime.now();
        this.messageContent = messageContent;
        this.sender = sender;
        this.recipient = recipient;
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

    public void setSender(AccountBean sender) {
        this.sender = sender;
    }

    public AccountBean getRecipient() {
        return recipient;
    }

    public void setRecipient(AccountBean recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}