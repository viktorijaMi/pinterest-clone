//package com.sorsix.pinterestclone.web
//
//import com.sorsix.pinterestclone.domain.User
//import com.sorsix.pinterestclone.service.UserService
//import org.springframework.http.ResponseEntity
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.annotation.AuthenticationPrincipal
//import org.springframework.web.bind.annotation.*
//import java.nio.channels.spi.AsynchronousChannelProvider.provider
//import java.util.*
//
//
//@RestController
//@RequestMapping("/api")
//class LoginController(
//    var userService: UserService
//) {
//
//    @GetMapping("/login")
//    fun getUserName(@AuthenticationPrincipal(expression="attributes['name']") username: String) : Map<String, String> {
//        return Collections.singletonMap("name", username)
//    }
//
//    @GetMapping("/user")
//    fun user(): ResponseEntity<User> {
////        val oAuth2Authentication: OAuth2Authentication
////        try {
////            oAuth2Authentication = authentication as OAuth2Authentication
////            val username: String = authentication.getName()
////            val user: User? = userService.getAuthenticatedUser()
////            if (user == null) {
////                userService.saveAuthenticatedUser(oAuth2Authentication)
////            }
////        } catch (e: Exception) {
////        }
////        val user: User = userService.getAuthenticatedUser()
////        return java.lang.String.format("%s", user.getUsername())
//        val user: User? = this.userService.getAuthenticatedUser()
//        if (user == null) {
//            return ResponseEntity.ok(user?.let { this.userService.saveAuthenticatedUser(it) })
//        }
//        return ResponseEntity.ok(user)
//    }
//
//
//}