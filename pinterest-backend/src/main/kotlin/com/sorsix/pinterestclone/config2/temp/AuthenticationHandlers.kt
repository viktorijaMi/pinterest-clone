//package com.sorsix.pinterestclone.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.http.HttpStatus
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.AuthenticationException
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.web.AuthenticationEntryPoint
//import org.springframework.security.web.authentication.AuthenticationFailureHandler
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
//import javax.servlet.http.HttpServletRequest
//import javax.servlet.http.HttpServletResponse
//
//
//@Configuration
//class AuthenticationHandlers {
//    @Bean
//    fun authenticationEntryPoint(): AuthenticationEntryPoint {
//        return AuthenticationEntryPoint { request, response, authenticationException ->
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied")
//        }
//    }
//
//    @Bean
//    fun successHandler(): AuthenticationSuccessHandler {
//        return AuthenticationSuccessHandler { request: HttpServletRequest?, response: HttpServletResponse, authentication: Authentication? ->
//            response.status = HttpStatus.OK.value()
//        }
//    }
//
//    @Bean
//    fun failureHandler(): AuthenticationFailureHandler {
//        return AuthenticationFailureHandler { request: HttpServletRequest?, response: HttpServletResponse, exception: AuthenticationException? ->
//            response.status = HttpStatus.UNAUTHORIZED.value()
//        }
//    }
//
//    @Bean
//    fun logoutSuccessHandler(): LogoutSuccessHandler {
//        return LogoutSuccessHandler { request: HttpServletRequest?, response: HttpServletResponse, authentication: Authentication? ->
//            response.status = HttpStatus.OK.value()
//        }
//    }
//}
