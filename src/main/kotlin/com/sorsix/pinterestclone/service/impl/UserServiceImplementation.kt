package com.sorsix.pinterestclone.service.impl

import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.UserNotFoundException
import com.sorsix.pinterestclone.repository.UserJpaRepository
import com.sorsix.pinterestclone.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImplementation(val repository: UserJpaRepository) : UserService {
    override fun login(username: String, password: String): User {
        TODO("Not yet implemented")
    }

    override fun register(username: String, password: String, repeatedPassword: String): User {
        TODO("Not yet implemented")
    }

    override fun findByUsername(username: String): User {
        return repository.findById(username)
            .orElseThrow { UserNotFoundException(String.format("User with username %s is not found", username)) }
    }
}