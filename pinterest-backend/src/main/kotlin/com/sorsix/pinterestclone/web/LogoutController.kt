package com.sorsix.pinterestclone.web

import com.sorsix.pinterestclone.config.TokenStore
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/logout")
class LogoutController(
    val tokenStore: TokenStore
) {
    @GetMapping
    fun logout() {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        this.tokenStore.removeToken(auth)
    }
}
