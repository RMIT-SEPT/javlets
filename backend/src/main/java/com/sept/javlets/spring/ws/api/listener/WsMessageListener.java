package com.sept.javlets.spring.ws.api.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.sept.javlets.spring.ws.api.model.WsMessage;

@Component
public class WsMessageListener {
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	 @EventListener
	    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
	        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

	        String username = (String) headerAccessor.getSessionAttributes().get("username");
	        if(username != null) {

	            WsMessage chatMessage = new WsMessage();
	            chatMessage.setType(WsMessage.MessageType.LEAVE);
	            chatMessage.setSender(username);

	            messagingTemplate.convertAndSend("/topic/public", chatMessage);
	        }
	    }
}
