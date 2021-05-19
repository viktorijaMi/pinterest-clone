package com.sorsix.pinterestclone.domain

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    var username: String,

    var password: String
)
