package com.sorsix.pinterestclone.service.impl

import com.sorsix.pinterestclone.domain.Favorite
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
class FavoriteServiceImplementation(
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

    override fun updateFavorite(pinId: Long, username: String): Favorite {
        val pin: Pin = this.pinService.findById(pinId)
        val user: User = this.userService.findByUsername(username)

        // update favorite
        if (this.findAll().stream().anyMatch { item -> item.pin.id== pinId }) {
            val favorite: Favorite = this.findByPinId(pinId)
            if (favorite.users.contains(user) && favorite.numFavorites > 0) {
                this.decreaseFavorites(favorite.id)
                favorite.users.remove(user)
            } else {
                this.increaseFavorites(favorite.id)
                favorite.users.add(user)
            }
            return this.repository.save(favorite)
        }
        // create favorite
        else {
            val users: MutableList<User> = mutableListOf(user)
            val favorite: Favorite = Favorite(0, 1, pin, users)
            return this.repository.save(favorite)
        }
    }

    override fun increaseFavorites(id: Long) {
        this.repository.increaseFavorites(id)
    }

    override fun decreaseFavorites(id: Long) {
        this.repository.decreaseFavorites(id)
    }
}