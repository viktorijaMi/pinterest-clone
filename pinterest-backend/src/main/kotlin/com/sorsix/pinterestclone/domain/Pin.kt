package com.sorsix.pinterestclone.domain

import javax.persistence.*

@Entity
data class Pin(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(length = 2048)
    var url: String,

    var description: String,

    var numFavorites: Int,

    @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL), orphanRemoval = true, mappedBy = "pin")
    var favorites: MutableList<Favorite>,

    @ManyToOne
    var createdBy: User,
)