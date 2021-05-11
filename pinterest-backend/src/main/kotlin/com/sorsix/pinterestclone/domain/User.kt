package com.sorsix.pinterestclone.domain

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    val username: String,

    val password: String,

    )