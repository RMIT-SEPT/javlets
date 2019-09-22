package com.sept.javlets.spring.ws.api.controller;

import com.sept.javlets.spring.ws.api.model.WsMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WsMessageController {
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public WsMessage sendmesageMessage(@Payload WsMessage chatMessage) {
		System.out.println("send message");
		return chatMessage;
	}
	
	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	public WsMessage register(@Payload WsMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		System.out.println("register");
		headerAccessor.getSessionAttributes().put("Username", chatMessage.getSender());
		return chatMessage;
	}	
}
