package com.yinnohs.gamesprer.token.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/token")
public class TokenController {

    @GetMapping("/check")
    public ResponseEntity<?> checkToken (){
        return ResponseEntity.ok("Token is correct");
    }
}
