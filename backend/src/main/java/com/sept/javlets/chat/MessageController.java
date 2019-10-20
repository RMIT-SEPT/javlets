package com.sept.javlets.chat;

import com.sept.javlets.mongo.MessageRepository;
import com.sept.javlets.mongo.UserRepository;
import com.sept.javlets.userauth.AccountBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

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


    // Gets chat log
    @GetMapping("/log")
    @ResponseBody
    public List<MessageBean> getConnectRequest(@RequestParam String id) {
        List<MessageBean> list = new ArrayList<>();

        String[] ids = id.split(" ");

        AccountBean user1 = null;
        AccountBean user2 = null;

        // Check if id exists
        if (userRepository.findById(ids[0]).isPresent()) {
            user1 = userRepository.findById(ids[0]).get();
        }

        if (userRepository.findById(ids[1]).isPresent()) {
            user2 = userRepository.findById(ids[1]).get();
        }
        if (user1 != null && user2 != null) {

            // Search for messages
            for (MessageBean msg : messageRepository.findAll()) {
                // User2 sent messages to user1 or vice versa
                if ((msg.getRecipientId().equals(user1.getId()) && msg.getSenderId().equals(user2.getId())) ||
                        (msg.getRecipientId().equals(user2.getId()) && msg.getSenderId().equals(user1.getId()))
                ) {
                    list.add(msg);
                }
            }
        }

        // Reverse to get the right order
        Collections.reverse(list);

        System.out.println("Returning messages of size: " + list.size() + " to " + user1.getId());
        return list;
    }


    @MessageMapping("/message")
    public void message(MessageBean mBean) {

        if (userRepository.findById(mBean.getSenderId()).isPresent() && userRepository.findById(mBean.getRecipientId()).isPresent()) {

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
        } else {
            System.out.println("Message failed to send");
        }
    }
}
