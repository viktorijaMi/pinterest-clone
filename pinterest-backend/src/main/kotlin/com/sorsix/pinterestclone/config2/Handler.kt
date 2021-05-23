package com.sorsix.pinterestclone.config2

import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.service.UserService
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Component
class Handler(
    val userService: UserService
): AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val a: OAuth2Authentication = authentication as OAuth2Authentication
        val map: Map<String, JvmType.Object> = a.userAuthentication.details as Map<String, JvmType.Object>

        val name: String
        if (map.get("name") != null) {
            name = map["name"].toString()
        } else {
            name = map["login"].toString()
        }
        val id: String = map["id"].toString()
        userService.addUser(User(id, name))
        response!!.sendRedirect("http://localhost:4200/pins")
    }
}