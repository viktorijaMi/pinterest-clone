package com.sorsix.pinterestclone.config

import com.sorsix.pinterestclone.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenStore(private val userService: UserService) {

    private final val cache: HashMap<String, Authentication> = HashMap<String, Authentication>()

    fun generateToken(authentication: Authentication): String {
        val user: OAuth2User = authentication.principal as OAuth2User
        val id = user.attributes["id"] as Int
        val username = user.attributes["login"].toString()
        val avatarUrl = user.attributes["avatar_url"].toString()
        this.userService.saveAuthenticatedUser(id, username, avatarUrl)
        val token: String = UUID.randomUUID().toString()
        cache[token] = authentication
        return token
    }

    fun removeToken(authentication: Authentication?): Boolean {
        if (authentication != null) {
            val token = this.cache.filter { it.value == authentication }.keys.first()
            return this.cache.remove(token) != null
        }
        return false
    }

    fun getAuth(token: String): Authentication? {
        return cache.getOrDefault(token, null);
    }

}
