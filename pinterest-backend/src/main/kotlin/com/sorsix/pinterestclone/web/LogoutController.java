package com.sorsix.pinterestclone.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom-logout")
public class LogoutController {

    @PostMapping
    public ResponseEntity<String> logout() {
        //remove from token store
        return ResponseEntity.ok("Logout successful");
    }
}
