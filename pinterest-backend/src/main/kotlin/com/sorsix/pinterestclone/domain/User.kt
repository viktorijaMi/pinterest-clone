package com.sorsix.pinterestclone.domain

//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
@Table(name="users")
data class User (
    @Id
    var username: String,

    var password: String
)
/*
@Entity
@Table(name = "users")
data class User(var username:String, var password:String) : UserDetails{

    @Id
    var username: String;

    var password: String;

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }
}
*/
