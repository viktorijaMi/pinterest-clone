package com.sorsix.pinterestclone.web

import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.UserNotFoundException
import com.sorsix.pinterestclone.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class UserController(
    val userService: UserService
) {

    @GetMapping("/user")
    fun user(@AuthenticationPrincipal principal: OAuth2User): Map<String?, Any?>? {
        return Collections.singletonMap("name", principal.getAttribute("name"))
    }

    /**
     * Error handlers
     * */
    @ExceptionHandler(UserNotFoundException::class)
    fun pinExceptionHandler(exception: UserNotFoundException): Map<String, String> {
        return mapOf("error" to exception.toString())
    }
}