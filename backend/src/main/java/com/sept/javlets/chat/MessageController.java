package com.sept.javlets.chat;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sept.javlets.userauth.AccountController;
import com.sept.javlets.userauth.StudentAccountBean;

@RestController
@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {
	
	private final MessageList messageList;
	private final AccountController accountController;

	public MessageController() {
		this.messageList = new MessageList();
		this.accountController = new AccountController();
	}
	
	public MessageController(AccountController accountController) {
		this.messageList = new MessageList();
		this.accountController = accountController;
	}
	
	@PostMapping(path="/chat/newMessage")
	public void newMessage(@RequestBody HashMap<String, String> chatInfo) {
		System.out.println("Received request CHAT");
		StudentAccountBean author = accountController.registerUser(chatInfo.get("from"));
		StudentAccountBean recipient = accountController.registerUser(chatInfo.get("to"));
		
		MessageBean post = new MessageBean(chatInfo.get("body"),author,recipient);
		messageList.addMessage(post);
	}
	
	@GetMapping(path="/chat/title")
	public MessageBean getMessages() {
		System.out.println("\nsending to front end:\n" + messageList.getAllMessages().get(1));
		return messageList.getAllMessages().get(0);
	}
	
	@GetMapping(path="/chat")
	public List<MessageBean> getAllMessages() {
		return messageList.getAllMessages();
	}

}
