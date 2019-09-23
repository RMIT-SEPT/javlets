package com.sept.javlets.chat;

import com.sept.javlets.mongo.MessageRepository;
import com.sept.javlets.mongo.UserRepository;
import com.sept.javlets.userauth.StudentAccountBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class MessageController {

    private final SimpMessagingTemplate template;

    @Autowired
    MessageController(SimpMessagingTemplate template){
        this.template = template;
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;


//    @PostMapping(path = "/newMessage")
//    public void add(@RequestBody HashMap<String, String> chatInfo) {
//        System.out.println("Received request CHAT");
//        System.out.println();
//
////		StudentAccountBean author = userRepository.findByUsername(chatInfo.get("from"));
////		StudentAccountBean recipient = userRepository.findByUsername(chatInfo.get("to"));
//        //Hardcoded values for testing
//        StudentAccountBean author = userRepository.findByUsername("Jamie");
//        StudentAccountBean recipient = userRepository.findByUsername("Chanboth");
//
//        MessageBean post = new MessageBean(chatInfo.get("body"), author, recipient);
//        messageRepository.save(post);
//
//    }
//
//    @GetMapping
//    public List<MessageBean> getAllMessages() {
//        return messageRepository.findAll();
//    }


    @MessageMapping("/message")
    public void message(MessageBean mBean) {
        userRepository.findById(mBean.getSenderId()).ifPresent(mBean::setSender);
        userRepository.findById(mBean.getRecipientId()).ifPresent(mBean::setRecipient);

        messageRepository.save(mBean);


        System.out.println("MESSAGE RECEIVED: " + mBean.getSender().getUsername() + " sent \"" + mBean.getMsg() + "\" to " + mBean.getRecipient());
        this.template.convertAndSend("/chat", mBean);
    }
}
