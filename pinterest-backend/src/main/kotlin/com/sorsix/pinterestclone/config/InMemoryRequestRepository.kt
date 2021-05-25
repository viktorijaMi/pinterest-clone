package com.sorsix.pinterestclone.config

import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class InMemoryRequestRepository : AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    private val cache: HashMap<String, OAuth2AuthorizationRequest> = HashMap<String, OAuth2AuthorizationRequest>()

    override fun loadAuthorizationRequest(request: HttpServletRequest): OAuth2AuthorizationRequest? {
        val state : String? = request.getParameter( "state" )
        if ( state != null ) {
            return removeAuthorizationRequest(request)
        }
        return null
    }

    override fun saveAuthorizationRequest(
        authorizationRequest: OAuth2AuthorizationRequest,
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ) {
        val state:String = authorizationRequest.state
        cache[state] = authorizationRequest;
    }

    override fun removeAuthorizationRequest(request: HttpServletRequest): OAuth2AuthorizationRequest? {
        val state: String? = request.getParameter("state")
        return if (state != null) {
            cache.remove(state)!!
        } else null

    }



}