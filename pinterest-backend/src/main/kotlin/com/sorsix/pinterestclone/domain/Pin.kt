package com.sorsix.pinterestclone.domain

import javax.persistence.*

@Entity
data class Pin(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    var url: String,

    var description: String,

    @ManyToOne
    private var user: User,

    )