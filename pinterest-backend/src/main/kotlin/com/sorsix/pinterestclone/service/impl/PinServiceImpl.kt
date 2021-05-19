package com.sorsix.pinterestclone.service.impl

import com.sorsix.pinterestclone.domain.Favorite
import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.PinNotFoundException
import com.sorsix.pinterestclone.repository.PinJpaRepository
import com.sorsix.pinterestclone.service.FavoriteService
import com.sorsix.pinterestclone.service.PinService
import com.sorsix.pinterestclone.service.UserService
import com.sorsix.pinterestclone.web.dto.PinDto
import org.springframework.stereotype.Service

@Service
class PinServiceImpl(
    val repository: PinJpaRepository,
    val userService: UserService,
//    val favoriteService: FavoriteService
) : PinService {

    override fun findAll(): List<Pin> {
        return repository.findAll()
    }

    override fun findById(id: Long): Pin {
        return repository.findById(id)
            .orElseThrow { PinNotFoundException(String.format("Pin with id %d is not found", id)) }
    }

    override fun findAllByUserId(username: String): List<Pin> {
        return repository.findAllByCreatedByUsername(username)
    }

    override fun savePin(pinDto: PinDto): Pin {
        val user: User = userService.findByUsername(pinDto.username)
        val favorites: MutableList<Favorite> = mutableListOf()
        val newPin = Pin(0, pinDto.url, pinDto.description, 0, favorites, user)
        return repository.save(newPin)
    }

    override fun deletePin(id: Long) {
//        this.favoriteService.deleteFavoriteByPin(pinId = id)
        repository.deleteById(id)
    }

    override fun updatePin(id: Long, description: String) {
        this.repository.updatePin(id, description)
    }

    override fun removeFavorite(pinId: Long, favorite: Favorite) {
        val pin: Pin = this.findById(pinId)
        pin.favorites.remove(favorite)
        pin.numFavorites--
        this.repository.save(pin)
    }

    override fun addFavorite(pinId: Long, favorite: Favorite) {
        val pin: Pin = this.findById(pinId)
        pin.favorites.add(favorite)
        pin.numFavorites++
        this.repository.save(pin)
    }
}