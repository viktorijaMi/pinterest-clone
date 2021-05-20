package com.sorsix.pinterestclone.config

import com.sorsix.pinterestclone.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(*[AuthenticationHandlers::class])
class SessionSecurityConfiguration(
    @Autowired
    var userService: UserService,

    @Autowired
    private val passwordEncoder: PasswordEncoder,

    @Autowired
    private val oauth2ClientContext: OAuth2ClientContext,

    @Autowired
    private val authenticationEntryPoint: AuthenticationEntryPoint,

    @Autowired
    private val logoutSuccessHandler : LogoutSuccessHandler,

    @Autowired
    private val successHandler : AuthenticationSuccessHandler,

    @Autowired
    private val failureHandler: AuthenticationFailureHandler
) : WebSecurityConfigurerAdapter() {

    @Autowired

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .httpBasic().disable()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
        http.formLogin()
            .loginProcessingUrl("/api/login")
            .successHandler(successHandler)
            .failureHandler(failureHandler)
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/api/logout")
            .logoutSuccessHandler(logoutSuccessHandler)
            .deleteCookies("JSESSIONID")
            .permitAll()
            .and()
            .headers()
            .frameOptions()
            .disable()
            .and()
            .addFilterBefore(ssoFilter(), BasicAuthenticationFilter::class.java)
        http.authorizeRequests()
            .antMatchers("/api/public*//**//**")
            .permitAll()
    }

    private fun ssoFilter(): OAuth2ClientAuthenticationProcessingFilter {
        val gitHubFilter = OAuth2ClientAuthenticationProcessingFilter("/api/login/code/github")
        val gitHubTemplate = OAuth2RestTemplate(github(), oauth2ClientContext)
        gitHubFilter.setRestTemplate(gitHubTemplate)
        val tokenServices = UserInfoTokenServices(githubResource()?.userInfoUri, github()?.clientId)
        tokenServices.setRestTemplate(gitHubTemplate)
        gitHubFilter.setTokenServices(tokenServices)
        return gitHubFilter
    }

    @Bean
    @ConfigurationProperties("github.client")
    fun github(): AuthorizationCodeResourceDetails? {
        return AuthorizationCodeResourceDetails()
    }

    @Bean
    @ConfigurationProperties("github.user-info-uri")
    fun githubResource(): ResourceServerProperties? {
        return ResourceServerProperties()
    }


//    @Bean
//    fun oauth2ClientFilterRegistration(
//        filter: OAuth2ClientContextFilter
//    ): FilterRegistrationBean<*>? {
//        val registration: FilterRegistrationBean<*> = FilterRegistrationBean(filter)
//        registration.order = -100
//        return registration
//    }


    init {
        this.userService = userService
        this.passwordEncoder = passwordEncoder
        this.oauth2ClientContext = oauth2ClientContext
    }
}

