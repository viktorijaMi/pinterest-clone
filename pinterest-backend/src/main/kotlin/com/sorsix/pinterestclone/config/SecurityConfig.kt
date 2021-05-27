package com.sorsix.pinterestclone.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.io.IOException
import java.util.*
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse



@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val mapper: ObjectMapper,
    private val tokenStore: TokenStore,
    private val tokenFilter: TokenFilter
) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http.csrf().disable().cors().and().authorizeRequests()
            .antMatchers("/oauth2/**", "/login**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .oauth2Login()
            .authorizationEndpoint()
            .authorizationRequestRepository(InMemoryRequestRepository())
            .and()
            .successHandler(this::successHandler)
            .and()
            .exceptionHandling()
            .authenticationEntryPoint( this::authenticationEntryPoint)
//            .and().logout().addLogoutHandler(this::logout).logoutSuccessHandler(this::onLogoutSuccess)
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    fun logout(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        System.out.println("Auth token is - " + request.getHeader( "Authorization" ))
    }

    @Bean
    fun corsConfigurationSource() : CorsConfigurationSource {
        val config: CorsConfiguration = CorsConfiguration()
        config.allowedMethods = Collections.singletonList( "*" );
        config.allowedOrigins = Collections.singletonList( "*" );
        config.allowedHeaders = Collections.singletonList( "*" );

        val source : UrlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration( "/**", config );
        return source;
    }

    @Throws(IOException::class, ServletException::class)
    fun onLogoutSuccess(
        request: HttpServletRequest?, response: HttpServletResponse,
        authentication: Authentication?
    ) {
        response.status = HttpServletResponse.SC_OK
    }

    @Throws(IOException::class)
    fun successHandler(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        val token : String = tokenStore.generateToken( authentication );
        response.sendRedirect("http://localhost:4200/dashboard?accessToken=$token")
    }

    @Throws(IOException::class)
    fun authenticationEntryPoint(request: HttpServletRequest, response: HttpServletResponse, authenticationException: AuthenticationException) {
        response.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
        response.getWriter().write( mapper.writeValueAsString( Collections.singletonMap( "error", "Unauthenticated" ) ) );
    }
}