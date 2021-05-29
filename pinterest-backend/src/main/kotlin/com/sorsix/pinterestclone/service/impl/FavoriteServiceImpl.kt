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

    override fun findAllByPinId(pinId: Long): List<Favorite> {
        return this.repository.findAllByPinId(pinId)
    }

    override fun findAll(): List<Favorite> {
        return this.repository.findAll()
    }

    override fun findById(id: Long): Favorite {
        return this.repository.findById(id)
            .orElseThrow { FavoriteNotFoundException(String.format("Favorite with id %d not found", id)) }
    }

    override fun findByPinId(pinId: Long): Favorite {
        return this.repository.findByPinId(pinId)
            .orElseThrow { FavoriteNotFoundException(String.format("Favorite with pinId %d not found", pinId)) }
    }

    override fun updateFavorite(pinId: Long, userId: Int): Favorite? {
        val favorite: Favorite

        if (repository.findByPinIdAndUserId(pinId, userId).isPresent) {
            favorite = repository.findByPinIdAndUserId(pinId, userId).get()
            this.pinService.removeFavorite(pinId, favorite)
            this.repository.delete(favorite)
        } else {
            val pin: Pin = this.pinService.findById(pinId)
            val user: User = this.userService.findById(userId)
            favorite = Favorite(FavoriteId(pinId, userId), pin, user)
            this.pinService.addFavorite(pinId, favorite)
            this.repository.save(favorite)
        }
        return favorite
    }

    override fun deleteFavoriteByPinId(pinId: Long) {
        val favorite: Favorite = this.findByPinId(pinId)
        this.repository.delete(favorite)
    }
}