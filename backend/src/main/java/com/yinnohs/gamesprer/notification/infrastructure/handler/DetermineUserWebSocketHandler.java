package com.yinnohs.gamesprer.notification.infrastructure.handler;

import com.sun.security.auth.UserPrincipal;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;
import org.springframework.lang.NonNull;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

public class DetermineUserWebSocketHandler extends DefaultHandshakeHandler {
        
    @Override
    protected Principal determineUser(
        @NonNull ServerHttpRequest request,
        @NonNull  WebSocketHandler wsHandler, 
        @NonNull Map<String, Object> attributes) {

        final String randomId = UUID.randomUUID().toString();
        return new UserPrincipal(randomId);
    }
    
}
