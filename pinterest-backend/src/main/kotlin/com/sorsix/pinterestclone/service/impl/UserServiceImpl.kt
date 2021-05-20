package com.sorsix.pinterestclone.service.impl

import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.InvalidArgumentsException
import com.sorsix.pinterestclone.exceptions.PasswordsDoNotMatchException
import com.sorsix.pinterestclone.exceptions.UserNotFoundException
import com.sorsix.pinterestclone.exceptions.UsernameAlreadyExistsException
import com.sorsix.pinterestclone.repository.UserJpaRepository
import com.sorsix.pinterestclone.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val repository: UserJpaRepository,
    val passwordEncoder: PasswordEncoder
) : UserService {

    override fun register(username: String, password: String, repeatedPassword: String): User {
        if (username.isEmpty() || password.isEmpty()) {
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

    override fun getAuthenticatedUser(auth2User: OAuth2Authentication): User {
        return findByUsername(auth2User.name)
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