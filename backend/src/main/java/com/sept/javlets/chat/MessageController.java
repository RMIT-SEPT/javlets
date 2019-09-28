package com.sept.javlets.chat;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sept.javlets.mongo.MessageRepository;
import com.sept.javlets.mongo.UserRepository;
import com.sept.javlets.userauth.AccountBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
    private final SimpMessagingTemplate template;
	
	@PostMapping(path="/newMessage")
	public void add(@RequestBody HashMap<String, String> chatInfo) {
		System.out.println("Received request CHAT");
		System.out.println();
		
//		AccountBean author = userRepository.findByUsername(chatInfo.get("from"));
//		AccountBean recipient = userRepository.findByUsername(chatInfo.get("to"));
		//Hardcoded values for testing
		AccountBean author = userRepository.findByUsername("Jamie");
		AccountBean recipient = userRepository.findByUsername("Chanboth");
		
		MessageBean post = new MessageBean(chatInfo.get("body"), author, recipient);
		messageRepository.save(post);
		
	}
	
	@GetMapping
	public List<MessageBean> getAllMessages() {
		return messageRepository.findAll();
	}

    @Autowired
    MessageController(SimpMessagingTemplate template){
        this.template = template;
    }


    @MessageMapping("/message")
    public void message(HashMap<String, String> messageInfo) {
        MessageBean message = new MessageBean(messageInfo.get("msg"), userRepository.findByUsername(messageInfo.get("senderId")));
        messageRepository.save(message);


        System.out.println("MESSAGE RECEIVED (" + message.getDate().toString() +"): " + message.getSender().getUsername() + " sent \"" + message.getMessageContent() + "\" to " + message.getRecipient());
        this.template.convertAndSend("/chat", message);
    }
    
}
