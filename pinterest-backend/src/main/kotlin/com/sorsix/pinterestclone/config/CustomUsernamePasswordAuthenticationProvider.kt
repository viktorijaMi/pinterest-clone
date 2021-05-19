package com.sorsix.pinterestclone.config

import com.sorsix.pinterestclone.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;



@Component
class CustomUsernamePasswordAuthenticationProvider(
    private val passwordEncoder: PasswordEncoder,
    private val userService: UserService
)  : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val username: String = authentication.name;
        val password: String = authentication.credentials.toString()

        if ("" == username || "" == password) {
            throw BadCredentialsException("Invalid Credentials")
        }

        val userDetails : UserDetails = this.userService.loadUserByUsername(username)

        if (!passwordEncoder.matches(password, userDetails.password)) {
            throw BadCredentialsException("Password is incorrect!")
        }
        return UsernamePasswordAuthenticationToken(userDetails, userDetails.password, userDetails.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}