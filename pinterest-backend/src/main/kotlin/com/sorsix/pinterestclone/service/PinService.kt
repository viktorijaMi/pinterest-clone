package com.sorsix.pinterestclone.service

import com.sorsix.pinterestclone.domain.Favorite
import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.web.dto.PinDto
import org.springframework.stereotype.Service


interface PinService {

    fun findById(id: Long): Pin

    fun findAll(): List<Pin>

    fun findAllByUserId(username: String): List<Pin>

    fun savePin(pinDto: PinDto, createdByUsername: String): Pin

    fun deletePin(id: Long)

    fun updatePin(id: Long, description: String)

    fun removeFavorite(pinId: Long, favorite: Favorite)

    fun addFavorite(pinId: Long, favorite: Favorite)
}