package com.sorsix.pinterestclone.service

import com.sorsix.pinterestclone.domain.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.provider.OAuth2Authentication


interface UserService : UserDetailsService {

    fun register(username: String, password: String, repeatedPassword: String): User

    fun findByUsername(username: String): User

    fun getAuthenticatedUser(auth2User: OAuth2Authentication) : User

    fun saveAuthenticatedUser(user: User) : User
}