//package com.sorsix.pinterestclone.config
//
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.stereotype.Component
//import org.springframework.web.filter.OncePerRequestFilter
//import java.io.IOException
//import javax.servlet.FilterChain
//import javax.servlet.ServletException
//import javax.servlet.http.HttpServletRequest
//import javax.servlet.http.HttpServletResponse
//
//
//@Component
//class TokenFilter(private final val tokenStore: TokenStore) : OncePerRequestFilter() {
//
//    @Throws(IOException::class, ServletException::class)
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        val authToken : String? = request.getHeader( "Authorization" )
//        if ( authToken != null ) {
//            val token: String = authToken.split( " " )[ 1 ]
//            val authentication: Authentication? = tokenStore.getAuth( token )
//            if ( authentication != null ) {
//                SecurityContextHolder.getContext().authentication = authentication
//            }
//        }
//        filterChain.doFilter( request, response );
//    }
//}