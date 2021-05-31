package com.sorsix.pinterestclone.service.impl

import com.sorsix.pinterestclone.domain.Favorite
import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.BadRequestException
import com.sorsix.pinterestclone.exceptions.PinNotFoundException
import com.sorsix.pinterestclone.repository.PinJpaRepository
import com.sorsix.pinterestclone.service.PinService
import com.sorsix.pinterestclone.service.UserService
import com.sorsix.pinterestclone.web.dto.PinDto
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class PinServiceImpl(
    val repository: PinJpaRepository,
    val userService: UserService,
) : PinService {

    override fun findAll(): List<Pin> {
        return repository.findAll()
    }

    override fun findById(id: Long): Pin {
        return repository.findById(id)
            .orElseThrow { PinNotFoundException(String.format("Pin with id %d is not found", id)) }
    }

    override fun findAllByCreatedById(id: Int): List<Pin> {
        return repository.findAllByCreatedById(id)
    }

    override fun savePin(pinDto: PinDto, createdById: Int): Pin {
        if (pinDto.description.isEmpty() || pinDto.url.isEmpty()){
            throw BadRequestException(String.format("Invalid pin description or url"))
        }
        val user: User = userService.findById(createdById)
        val favorites: MutableList<Favorite> = mutableListOf()
        val newPin = Pin(0, pinDto.url, pinDto.description, 0, favorites, user)
        return repository.save(newPin)
    }

    override fun deletePin(id: Long) {
        val user: OAuth2User = SecurityContextHolder.getContext().authentication. principal as OAuth2User
        val pin: Pin = this.findById(id)
        if (user.attributes["id"] != pin.createdBy.id){
            throw BadRequestException(String.format("User is not authorized to delete pin with id %d", id))
        }
        this.repository.deleteById(id)
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