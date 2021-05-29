package com.sorsix.pinterestclone.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
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
    //TODO: Why do we have a table user_authorities?
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().cors().and().authorizeRequests()
            .antMatchers("/oauth2/**", "/login**", "/api/pins/all").permitAll()
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
            .authenticationEntryPoint(this::authenticationEntryPoint)
            .and().logout()
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()
        config.allowedMethods = Collections.singletonList("*");
        config.allowedOrigins = Collections.singletonList("*");
        config.allowedHeaders = Collections.singletonList("*");

        val source = UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Throws(IOException::class)
    fun successHandler(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        val token: String = tokenStore.generateToken(authentication);
        response.sendRedirect("http://localhost:4200/callback?accessToken=$token")
    }

    @Throws(IOException::class)
    fun authenticationEntryPoint(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authenticationException: AuthenticationException
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED;
        response.writer.write(mapper.writeValueAsString(Collections.singletonMap("error", "Unauthenticated")));
    }
}