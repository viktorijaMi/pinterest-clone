package com.sorsix.pinterestclone.service

import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.web.dto.PinDto
import org.springframework.stereotype.Service


interface PinService {

    fun findById(id: Long): Pin

    fun findAll(): List<Pin>

    fun addPin(url: String, description: String, username: String): Pin

    fun save(pinDto: PinDto): Pin

    fun deletePin(id: Long)

    fun updatePin(id: Long, description: String)

    fun increaseFavourites(id: Long)

    fun findAllByUserId(username: String): List<Pin>
}