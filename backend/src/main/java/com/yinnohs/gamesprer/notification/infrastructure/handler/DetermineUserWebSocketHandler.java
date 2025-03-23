package com.yinnohs.gamesprer.notification.infrastructure.handler;

import com.sun.security.auth.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import org.springframework.lang.NonNull;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Slf4j
public class DetermineUserWebSocketHandler extends DefaultHandshakeHandler {
        
    @Override
    protected Principal determineUser(
        @NonNull ServerHttpRequest request,
        @NonNull WebSocketHandler wsHandler, 
        @NonNull Map<String, Object> attributes) {
            
        String userId = null;
        try {
            userId = extractUserIdFromRequest(request);
        } catch (IOException e) {
            log.error(e.getMessage());      
        }
        
        if (userId == null) {
            // For debugging, log the headers
            System.out.println("Headers received: " + request.getHeaders());
            // Generate a random ID as fallback (for development only)
            userId = "user-" + System.currentTimeMillis();
        }
        
        // Log the user principal creation
        System.out.println("Creating WebSocket principal for user: " + userId);
        return new UserPrincipal(userId);
    }
    
    private String extractUserIdFromRequest(ServerHttpRequest request) throws IOException {
        String token = request.getHeaders().getFirst("Authorization");
        System.out.println("Received token: " + token);
        
        if (token != null && token.startsWith("Bearer ")) {
            // Here you should properly decode and validate your JWT token
            // For now, let's just extract a user ID from the query parameters
            String query = request.getURI().getQuery();
            if (query != null && query.contains("userId=")) {
                return query.split("userId=")[1].split("&")[0];
            }
        }
        return null;
    }
}
