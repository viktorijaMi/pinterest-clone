package com.sorsix.pinterestclone.web

import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.*
import com.sorsix.pinterestclone.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation.*


@RestController
@EnableOAuth2Client
@RequestMapping("/api")
class LoginController(
    val loginService: UserService
) {

    @PostMapping("public/registration")
    fun register(@RequestBody registrationForm: Map<String, String>): ResponseEntity<User> {
        val username = registrationForm["username"]
        val password = registrationForm["password"]
        val repeatedPassword = registrationForm["repeatedPassword"]
        return loginService.register(username, password, repeatedPassword).let {
            ResponseEntity.ok(it)
        }
    }

    @GetMapping("/user")
    fun user(authentication: Authentication?): ResponseEntity<User> {
        val oAuth2Authentication: OAuth2Authentication = authentication as OAuth2Authentication
        val name: String = oAuth2Authentication.name
        val user: User = loginService.findByUsername(name)
        return loginService.saveAuthenticatedUser(user).let {
            ResponseEntity.ok(it)
        }
    }

    /**
     * Error handlers
     * */
    @ExceptionHandler(UserNotFoundException::class)
    fun userNotFoundExceptionHandler(exception: UserNotFoundException): Map<String, String> {
        return mapOf("error" to exception.toString())
    }

    @ExceptionHandler(InvalidArgumentsException::class)
    fun invalidArgumentsExceptionHandler(exception: InvalidArgumentsException): Map<String, String> {
        return mapOf("error" to exception.toString())
    }

    @ExceptionHandler(PasswordsDoNotMatchException::class)
    fun passwordsDoNotMatchExceptionHandler(exception: PasswordsDoNotMatchException): Map<String, String> {
        return mapOf("error" to exception.toString())
    }

    @ExceptionHandler(UsernameAlreadyExistsException::class)
    fun usernameAlreadyExistsExceptionHandler(exception: UsernameAlreadyExistsException): Map<String, String> {
        return mapOf("error" to exception.toString())
    }


}