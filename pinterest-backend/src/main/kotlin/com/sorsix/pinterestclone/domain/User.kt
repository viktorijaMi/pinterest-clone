package com.sorsix.pinterestclone.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

//@Entity
//@Table(name = "users")
//data class User(
//    @Id
//    private var username: String,
//    private var password: String,
//) : UserDetails {
//
//    @ElementCollection
//    private var authorities: MutableList<GrantedAuthority> =
//        mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
//
//    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
//        return this.getAuthorities();
//    }
//
//    override fun getPassword(): String {
//        return this.getPassword();
//    }
//
//    override fun getUsername(): String {
//        return this.getUsername();
//    }
//
//    override fun isAccountNonExpired(): Boolean {
//        return true;
//    }
//
//    override fun isAccountNonLocked(): Boolean {
//        return true;
//    }
//
//    override fun isCredentialsNonExpired(): Boolean {
//        return true;
//    }
//
//    override fun isEnabled(): Boolean {
//        return true;
//    }
//}

@Entity
@Table(name="pin_users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var username: String,
    var name: String
)