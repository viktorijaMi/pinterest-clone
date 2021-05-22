package com.sorsix.pinterestclone.web

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class LoginController() {

    @GetMapping
    fun getUserName(@AuthenticationPrincipal(expression="attributes['name']") username: String) : String {
        return username
    }


}