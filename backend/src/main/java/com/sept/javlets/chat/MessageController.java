package com.sept.javlets.chat;

import com.sept.javlets.mongo.MessageRepository;
import com.sept.javlets.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Random;

@Controller
public class MessageController {

    private final SimpMessagingTemplate template;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    MessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/message")
    public void message(MessageBean mBean) {

        if(userRepository.findById(mBean.getSenderId()).isPresent() && userRepository.findById(mBean.getRecipientId()).isPresent()) {

            mBean.setSender(userRepository.findById(mBean.getSenderId()).get());
            mBean.setRecipient(userRepository.findById(mBean.getRecipientId()).get());

            // Date
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
            Calendar cal = Calendar.getInstance();

            mBean.setDate(sdf.format(cal.getTime()));
            mBean.setId(Long.toString(new Random().nextLong()));
            messageRepository.save(mBean);


            System.out.println("MESSAGE RECEIVED (" + LocalDateTime.now().toString() + "): " + mBean.getSender().getId() + " sent \"" + mBean.getMessageContent() + "\" to " + mBean.getRecipientId());
            this.template.convertAndSend("/chat", mBean);
        }else{
            System.out.println("Message failed to send");
        }
    }
}
