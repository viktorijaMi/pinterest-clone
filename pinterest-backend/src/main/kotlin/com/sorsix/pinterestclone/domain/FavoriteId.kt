package com.sorsix.pinterestclone.domain

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class FavoriteId : Serializable {

    var pin_id: Long

    var user_username: String

    constructor(pin_id: Long, user_username: String) {
        this.pin_id = pin_id
        this.user_username = user_username
    }

    override fun equals(other: Any?): Boolean {
        val favoriteId: FavoriteId = other as FavoriteId
        return this.pin_id == favoriteId.pin_id && this.user_username.equals(favoriteId.user_username)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}