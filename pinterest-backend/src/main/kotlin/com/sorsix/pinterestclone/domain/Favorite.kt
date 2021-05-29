package com.sorsix.pinterestclone.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Favorite(
    @EmbeddedId
    var id: FavoriteId,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pinId")
    @MapsId("pinId")
    var pin: Pin,

    @ManyToOne
    @MapsId("userId")
    var user: User

)