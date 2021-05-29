package com.sorsix.pinterestclone.service.impl

import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.UserNotFoundException
import com.sorsix.pinterestclone.repository.UserJpaRepository
import com.sorsix.pinterestclone.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImpl(
    val repository: UserJpaRepository
) : UserService {

    override fun findById(id: Int): User {
        return repository.findById(id)
            .orElseThrow { UserNotFoundException(String.format("User with id %d is not found", id)) }
    }

    override fun findByUsername(username: String): User {
        return repository.findByUsername(username)
            .orElseThrow { UserNotFoundException(String.format("User with username %s is not found", username)) }
    }

    override fun getAuthenticatedUser(): User? {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val user: OAuth2User = authentication.principal as OAuth2User
        val id = user.attributes["id"] as Int
        return this.findById(id)
    }

    override fun saveAuthenticatedUser(id: Int, username: String, avatarUrl: String): User {
        if (this.repository.findById(id).isPresent) {
            return this.repository.findById(id).get()
        }
        val user = User(id, username, avatarUrl)
        return this.repository.save(user)
    }
}