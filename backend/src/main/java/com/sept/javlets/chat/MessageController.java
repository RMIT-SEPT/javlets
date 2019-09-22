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
import com.sept.javlets.userauth.StudentAccountBean;

//@CrossOrigin(origins = "http://javlet.social")
@RestController
@RequestMapping("/chat")
public class MessageController {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(path="/newMessage")
	public void add(@RequestBody HashMap<String, String> chatInfo) {
		System.out.println("Received request CHAT");
		System.out.println();
		
//		StudentAccountBean author = userRepository.findByUsername(chatInfo.get("from"));
//		StudentAccountBean recipient = userRepository.findByUsername(chatInfo.get("to"));
		//Hardcoded values for testing
		StudentAccountBean author = userRepository.findByUsername("Jamie");
		StudentAccountBean recipient = userRepository.findByUsername("Chanboth");
		
		MessageBean post = new MessageBean(chatInfo.get("body"), author, recipient);
		messageRepository.save(post);
		
	}
	
	@GetMapping
	public List<MessageBean> getAllMessages() {
		return messageRepository.findAll();
	}

}
