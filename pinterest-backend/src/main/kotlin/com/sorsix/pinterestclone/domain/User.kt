package com.sorsix.pinterestclone.domain

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    var id: Int,

    var username: String,

    var avatarUrl: String

)  {

}