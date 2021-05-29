package com.sorsix.pinterestclone.service

import com.sorsix.pinterestclone.domain.Favorite
import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.web.dto.PinDto

interface PinService {

    fun findAll(): List<Pin>

    fun findAllByUserId(id: Int): List<Pin>

    fun findById(id: Long): Pin

    fun savePin(pinDto: PinDto, createdById: Int): Pin

    fun deletePin(id: Long)

    fun removeFavorite(pinId: Long, favorite: Favorite)

    fun addFavorite(pinId: Long, favorite: Favorite)
}