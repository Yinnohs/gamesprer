package com.yinnohs.gamesprer.notification.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketNotificationConfiguration implements WebSocketMessageBrokerConfigurer {
    
    @Value("${web.socket.topic}")
    private String topic;
    @Value("${web.socket.prefix}")
    private String prefix;
    @Value("${web.socket.endpoint}")
    private String endpoint;
    @Value("${web.socket.allowed.origins}")
    private String allowedOrigins;


    @Override
    public void registerStompEndpoints( @NonNull StompEndpointRegistry registry) {
        registry.addEndpoint(endpoint)
        .setAllowedOrigins(allowedOrigins)
        .withSockJS();
    }

    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry config) {
        config.enableSimpleBroker(topic);
        config.setApplicationDestinationPrefixes(prefix);
    }

}
