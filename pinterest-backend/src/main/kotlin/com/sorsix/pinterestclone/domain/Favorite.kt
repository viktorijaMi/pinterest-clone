package com.sorsix.pinterestclone.domain

import javax.persistence.*

@Entity
data class Favorite(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    var numFavorites: Int,

    @ManyToOne
    var pin: Pin,

    @OneToMany
    var users: MutableList<User>

)