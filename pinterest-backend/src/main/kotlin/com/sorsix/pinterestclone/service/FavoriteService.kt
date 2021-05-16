package com.sorsix.pinterestclone.service

import com.sorsix.pinterestclone.domain.Favorite
import java.util.*

interface FavoriteService {

    fun findById(id: Long): Favorite

    fun findByPinId(pinId: Long): Favorite

    fun findAllByPinId(id: Long): List<Favorite>

    fun findAll(): List<Favorite>

    fun updateFavorite(pinId: Long, username: String): Favorite?

    fun deleteFavoriteByPin(pinId: Long)
}