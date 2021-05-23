package com.sorsix.pinterestclone.config2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurity(
    @Qualifier("oauth2ClientContext") @Autowired
    val oauth2ClientContext: OAuth2ClientContext,

    @Autowired
    val handler: Handler

) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/**", "/login**", "/api/**")
            .permitAll()
            .anyRequest()
            .authenticated().and().logout()
            .logoutSuccessUrl("http://localhost:4200/dashboard").permitAll().and().csrf().disable()
            .addFilterBefore(ssoFilter(), BasicAuthenticationFilter::class.java)
    }


    private fun ssoFilter() : OAuth2ClientAuthenticationProcessingFilter {
        val filter = OAuth2ClientAuthenticationProcessingFilter("/login/oauth/github")
        filter.setAuthenticationSuccessHandler(handler)
        val template = OAuth2RestTemplate(github().client, oauth2ClientContext)
        filter.setRestTemplate(template)
        val tokenServices = UserInfoTokenServices(github().resource.userInfoUri, github().client.clientId)
        tokenServices.setRestTemplate(template)
        filter.setTokenServices(tokenServices)
        return filter
    }

    @Bean
    @ConfigurationProperties("github")
    fun github(): ClientResources {
        return ClientResources()
    }


}