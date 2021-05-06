package com.sorsix.pinterestclone.domain

import javax.persistence.*

@Entity
data class Pin (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id : Long,

    private val url : String,

    private val description : String,

    private val favorites : Int,

    @ManyToOne
    private val user : User,
    )