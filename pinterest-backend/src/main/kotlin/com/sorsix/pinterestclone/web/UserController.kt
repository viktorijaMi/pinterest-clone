package com.sorsix.pinterestclone.web

import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.PinNotFoundException
import com.sorsix.pinterestclone.exceptions.UserNotFoundException
import com.sorsix.pinterestclone.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api")
class UserController(
    val userService: UserService
) {

   @GetMapping("/user")
   fun user(principal: Principal) : Principal {
       return principal
   }

   @GetMapping("/{user}")
   fun getUser(@PathVariable user: String) : ResponseEntity<User> {
       return userService.getUser(user).let {
           ResponseEntity.ok(it)
       }
   }

   @PostMapping("/post")
   fun addUser(@RequestBody user: User) : ResponseEntity<User> {
       return userService.addUser(user).let {
           ResponseEntity.ok(it)
       }
   }

    /**
     * Error handlers
     * */
    @ExceptionHandler(UserNotFoundException::class)
    fun pinExceptionHandler(exception: UserNotFoundException): Map<String, String> {
        return mapOf("error" to exception.toString())
    }
}