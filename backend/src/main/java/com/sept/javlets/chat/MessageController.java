package com.sept.javlets.chat;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sept.javlets.userauth.AccountController;
import com.sept.javlets.userauth.StudentAccountBean;

@RestController
public class MessageController {
	
	private final MessageList messageList;
	private final AccountController accountController;

	public MessageController(AccountController accountController) {
		this.messageList = new MessageList();
		this.accountController = accountController;
	}
	
	@PostMapping(value="/")
	public MessageBean newMessage(@RequestParam("message") String messageContent, @RequestParam("sender") String sender, @RequestParam("recipient") String recipient) {
		MessageBean ret = null;
		StudentAccountBean senderAccount = accountController.getUser(sender);
		StudentAccountBean recipientAccount = accountController.getUser(recipient);
		
		if (senderAccount != null && recipientAccount != null) {
			MessageBean message = new MessageBean(messageContent, senderAccount, recipientAccount);
			messageList.addMessage(message);
		}
		
		// Returns a null value if either of the users don't exist - error messages to be thrown on frontend
		return ret;
	}

}
