package com.sorsix.pinterestclone.service.impl

import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.UserNotFoundException
import com.sorsix.pinterestclone.repository.UserJpaRepository
import com.sorsix.pinterestclone.service.UserService
import com.sorsix.pinterestclone.web.dto.UserDto
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImpl(
    val repository: UserJpaRepository) : UserService {


    override fun findByUsername(username: String): User {
        return repository.findById(username)
            .orElseThrow { UserNotFoundException(String.format("User with username %s is not found", username)) }
    }

    override fun getAuthenticatedUser(): User? {
//        return findByUsername(auth2User.name)
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val currentPrincipalName: String = authentication.getName()
        if (currentPrincipalName == "anonymousUser")
            return null
        return this.findByUsername(currentPrincipalName)

    }

    override fun saveAuthenticatedUser(username: String): User {
        if (this.repository.findById(username).isPresent) {
            return this.repository.findById(username).get()
        }
        val user: User = User(username)
        return this.repository.save(user)
    }

}