package com.sorsix.pinterestclone.service

import com.sorsix.pinterestclone.domain.Favorite
import java.util.*

interface FavoriteService {

    fun findAll(): List<Favorite>

    fun findAllByPinId(pinId: Long): List<Favorite>

    fun findById(id: Long): Favorite

    fun findByPinId(pinId: Long): Favorite

    fun updateFavorite(pinId: Long, userId: Int): Favorite

    fun deleteFavoriteByPinId(pinId: Long)
}