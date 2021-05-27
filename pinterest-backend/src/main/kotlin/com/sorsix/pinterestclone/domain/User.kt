package com.sorsix.pinterestclone.domain

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    private var username: String,

)  {

}