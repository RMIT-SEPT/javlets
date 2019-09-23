package com.sept.javlets.chat;

import com.sept.javlets.userauth.StudentAccountBean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "messages")
public class MessageBean {

    @Id
    private String id;

    private String msg;
    private StudentAccountBean sender;
    private StudentAccountBean recipient;
    private LocalDateTime date;

    public MessageBean(String msg, StudentAccountBean sender, StudentAccountBean recipient) {
        this.date = LocalDateTime.now();
        this.msg = msg;
        this.sender = sender;
        this.recipient = recipient;
    }

    public MessageBean() {
        super();
    }

    public MessageBean(String msg, StudentAccountBean sender) {
        this.date = LocalDateTime.now();
        this.msg = msg;
        this.sender = sender;
    }

    public MessageBean(String msg) {
        this.date = LocalDateTime.now();
        this.msg = msg;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
