package com.sorsix.pinterestclone.config

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenStore{

    private final val cache: HashMap<String, Authentication> = HashMap<String, Authentication>()

    fun generateToken(authentication: Authentication) : String{
        val token : String = UUID.randomUUID().toString()
        cache[token] = authentication
        return token;
    }

    fun getAuth( token : String ) : Authentication?
    {
        return cache.getOrDefault(token, null);
    }

}
