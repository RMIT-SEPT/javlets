package com.sept.javlets.chat;

import com.sept.javlets.mongo.UserRepository;
import com.sept.javlets.userauth.StudentAccountBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


@Document(collection = "messages")
public class MessageBean {

    @Id
    private String id;

    private String msg;

    private String senderId;
    private String recipientId;

    private String datetime;

    private StudentAccountBean sender;
    private StudentAccountBean recipient;

    MessageBean(String msg, String senderId, String recipientId) {
        this.id = Long.toString(new Random().nextLong()); // Using long to ID since time is unreliable if sent message at same time
        this.datetime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
        this.msg = msg;
        this.senderId = senderId;
        this.recipientId = recipientId;
    }

    public MessageBean() {
        super();
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public String getDateTime() {
        return datetime;
    }

    public void getDateTime(String date) {
        this.datetime = date;
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

}
