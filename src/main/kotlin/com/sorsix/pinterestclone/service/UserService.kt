package com.sorsix.pinterestclone.service

import com.sorsix.pinterestclone.domain.User
import org.springframework.stereotype.Service


interface UserService {

    fun login(username: String, password: String): User

    fun register(username: String, password: String, repeatedPassword: String): User

    fun findByUsername(username: String): User
}