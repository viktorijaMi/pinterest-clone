package com.sorsix.pinterestclone.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Favorite(
    @EmbeddedId
    var id: FavoriteId,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pin_id")
    @MapsId("pin_id")
    var pin: Pin,

    @ManyToOne
    @MapsId("user_username")
    var user: User

)