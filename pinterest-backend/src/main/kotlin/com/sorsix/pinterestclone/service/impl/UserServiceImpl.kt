package com.sorsix.pinterestclone.service.impl

import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.InvalidArgumentsException
import com.sorsix.pinterestclone.exceptions.PasswordsDoNotMatchException
import com.sorsix.pinterestclone.exceptions.UserNotFoundException
import com.sorsix.pinterestclone.exceptions.UsernameAlreadyExistsException
import com.sorsix.pinterestclone.repository.UserJpaRepository
import com.sorsix.pinterestclone.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImpl(
    val repository: UserJpaRepository,
    val passwordEncoder: PasswordEncoder) : UserService {

        override fun register(username: String?, password: String?, repeatedPassword: String?): User {
        if (username == null || password == null) {
            throw InvalidArgumentsException(String.format("Invalid username or password!"))
        }
        if (!password.equals(repeatedPassword))
            throw PasswordsDoNotMatchException(String.format("Passwords do not match!"));
        if (this.repository.findById(username).isPresent())
            throw UsernameAlreadyExistsException(String.format("User with username %s already exists", username));
        val user: User = User(username, passwordEncoder.encode(password));
        return repository.save(user);
    }

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

    override fun saveAuthenticatedUser(user: User): User {
        if (this.repository.findById(user.username).isPresent) {
            return user
        }
        return this.repository.save(user)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return this.findByUsername(username)
    }
}