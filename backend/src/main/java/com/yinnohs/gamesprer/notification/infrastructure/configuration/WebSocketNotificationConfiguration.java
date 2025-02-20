package com.yinnohs.gamesprer.notification.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.yinnohs.gamesprer.notification.infrastructure.handler.DetermineUserWebSocketHandler;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketNotificationConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(@NonNull final MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints( @NonNull final StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
        .setAllowedOrigins("localhost:8080")
        .setHandshakeHandler(new DetermineUserWebSocketHandler())
        .withSockJS();
    }

}
