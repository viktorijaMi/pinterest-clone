package com.sorsix.pinterestclone.domain

import javax.persistence.*

@Entity
@Table(name = "users")
data class User (

    @Id
    private val username : String,

    private val password : String,

    )