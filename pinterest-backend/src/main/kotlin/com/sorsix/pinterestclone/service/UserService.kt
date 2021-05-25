package com.sorsix.pinterestclone.service

import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.web.dto.UserDto
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

interface UserService{

//    fun register(username: String?, password: String?, repeatedPassword: String?): User

   fun findByUsername(username: String): User

    fun getAuthenticatedUser() : User?

    fun saveAuthenticatedUser(userDto: UserDto) : User
}