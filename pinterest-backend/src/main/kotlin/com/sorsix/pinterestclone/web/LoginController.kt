package com.sorsix.pinterestclone.web

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/login")
class LoginController() {

    @GetMapping
    fun getUserName(@AuthenticationPrincipal(expression="attributes['name']") username: String) : Map<String, String> {
        return Collections.singletonMap("name", username)
    }


}