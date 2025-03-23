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
        config.enableSimpleBroker("/topic", "/queue", "/user");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints( @NonNull final StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
        .setAllowedOrigins(
            "http://localhost:5052",
            "http://localhost:5053",
            "http://192.168.1.49:5052",
            "http://192.168.1.49:5053",
            "http://90.171.77.134:5052",
            "http://90.171.77.134:5053",
            "http://localhost:8080",
            "http://192.168.1.49:8080",
            "http://90.171.77.134:8080"
        )
        .setHandshakeHandler(new DetermineUserWebSocketHandler())
        .withSockJS();
    }

}
