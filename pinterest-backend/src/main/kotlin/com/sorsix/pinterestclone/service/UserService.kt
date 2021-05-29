package com.sorsix.pinterestclone.service

import com.sorsix.pinterestclone.domain.User
import java.util.*

interface UserService {

    fun findById(id: Int): User

    fun findByUsername(username: String): User

    fun getAuthenticatedUser(): User?

    fun saveAuthenticatedUser(id: Int, username: String, avatarUrl: String): User
}