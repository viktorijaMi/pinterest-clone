package com.sorsix.pinterestclone.domain

import java.io.Serializable
import java.lang.StringBuilder
import javax.persistence.Embeddable

@Embeddable
class FavoriteId : Serializable {

    var pinId: Long

    var userId: Int

    constructor(pinId: Long, userId: Int) {
        this.pinId = pinId
        this.userId = userId
    }

    override fun equals(other: Any?): Boolean {
        val favoriteId: FavoriteId = other as FavoriteId
        return this.pinId == favoriteId.pinId && this.userId == favoriteId.userId
    }

    override fun hashCode(): Int {
        val sb = StringBuilder()
        sb.append(pinId).append(userId)
        return Integer.parseInt(sb.toString())
    }
}