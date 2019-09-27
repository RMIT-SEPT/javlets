package com.sept.javlets.chat;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class MessageController {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
        String datetime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()).toString();

        userRepository.findById(mBean.getSenderId()).ifPresent(mBean::setSender);
        userRepository.findById(mBean.getRecipientId()).ifPresent(mBean::setRecipient);
        mBean.setDateTime(datetime);
        messageRepository.save(mBean);


        System.out.println("MESSAGE RECEIVED (" + datetime +"): " + mBean.getSender().getUsername() + " sent \"" + mBean.getMsg() + "\" to " + mBean.getRecipient());
        this.template.convertAndSend("/chat", mBean);
    }
}
