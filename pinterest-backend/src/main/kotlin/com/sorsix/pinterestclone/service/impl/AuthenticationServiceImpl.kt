package com.sorsix.pinterestclone.service.impl

import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.InvalidArgumentsException
import com.sorsix.pinterestclone.exceptions.InvalidCredentialsException
import com.sorsix.pinterestclone.repository.UserJpaRepository
import com.sorsix.pinterestclone.service.AuthenticationService
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(val repository: UserJpaRepository) : AuthenticationService {
    override fun login(username: String, password: String): User {
        if (username.isEmpty() || password.isEmpty()) {
            throw InvalidArgumentsException(String.format("Invalid username or password!"))
        }
        return this.repository.findByUsernameAndPassword(
            username,
            password
        ).orElseThrow {
            InvalidCredentialsException(String.format("Wrong username or password!"))
        }
    }
}