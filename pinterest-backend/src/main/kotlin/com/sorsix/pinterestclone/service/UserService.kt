package com.sorsix.pinterestclone.service

import com.sorsix.pinterestclone.domain.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

interface UserService : UserDetailsService{

    fun register(username: String?, password: String?, repeatedPassword: String?): User

   fun findByUsername(username: String): User

    fun getAuthenticatedUser() : User?

    fun saveAuthenticatedUser(user: User) : User
}