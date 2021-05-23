package com.sorsix.pinterestclone.service.impl

import com.sorsix.pinterestclone.domain.Favorite
import com.sorsix.pinterestclone.domain.FavoriteId
import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.FavoriteNotFoundException
import com.sorsix.pinterestclone.repository.FavoriteJpaRepository
import com.sorsix.pinterestclone.service.FavoriteService
import com.sorsix.pinterestclone.service.PinService
import com.sorsix.pinterestclone.service.UserService
import org.springframework.stereotype.Service
import java.util.*

@Service
class FavoriteServiceImpl(
    val repository: FavoriteJpaRepository,
    val userService: UserService,
    val pinService: PinService
) : FavoriteService {

    override fun findById(id: Long): Favorite {
        return this.repository.findById(id)
            .orElseThrow { FavoriteNotFoundException(String.format("Favorite with id %d not found", id)) }
    }

    override fun findByPinId(pinId: Long): Favorite {
        return this.repository.findByPinId(pinId)
            .orElseThrow { FavoriteNotFoundException(String.format("Favorite with pinId %d not found", pinId)) }
    }

    override fun findAllByPinId(id: Long): List<Favorite> {
        return this.repository.findAllByPinId(id)
    }

    override fun findAll(): List<Favorite> {
        return this.repository.findAll()
    }

    override fun updateFavorite(pinId: Long, username: String): Favorite? {
        val favorite: Favorite

        if (repository.findByPinIdAndUserUsername(pinId, username).isPresent) {
            favorite = repository.findByPinIdAndUserUsername(pinId, username).get()
            this.pinService.removeFavorite(pinId, favorite)
            this.repository.delete(favorite)
        } else {
            val pin: Pin = this.pinService.findById(pinId)
            val user: User = this.userService.getUser(username)
            favorite = Favorite(FavoriteId(pinId, username), pin, user)
            this.pinService.addFavorite(pinId, favorite)
            this.repository.save(favorite)
        }
        return favorite
    }

    override fun deleteFavoriteByPin(pinId: Long) {
        val favorite: Favorite = this.findByPinId(pinId)
        this.repository.delete(favorite)
    }
}