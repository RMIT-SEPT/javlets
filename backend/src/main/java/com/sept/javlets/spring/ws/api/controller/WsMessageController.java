package com.sept.javlets.spring.ws.api.controller;

import com.sept.javlets.spring.ws.api.model.WsMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WsMessageController {

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public WsMessage register(@Payload WsMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("Username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public WsMessage sendmesageMessage(@Payload WsMessage chatMessage) {
        return chatMessage;
    }

}
