package com.sept.javlets.spring.ws.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WsConfig implements WebSocketMessageBrokerConfigurer{
	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/javlets").withSockJS();
        System.out.println("in config1");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        System.out.println("in config2");

        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
//        registry.enableStompBrokerRelay("/topic")
//        .setRelayHost("localhost")
//        .setRelayPort(3000)
//        .setClientLogin("guest")
//        .setClientPasscode("guest");
    }
	
}
