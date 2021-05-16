package com.sorsix.pinterestclone.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import javax.persistence.*

@Entity
@Embeddable
data class Pin(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    var url: String,

    var description: String,

    var numFavorites: Int,

    @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
    var favorites: MutableList<Favorite>,

    @ManyToOne
    var createdBy: User,
)