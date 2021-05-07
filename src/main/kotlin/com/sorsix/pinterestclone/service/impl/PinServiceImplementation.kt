package com.sorsix.pinterestclone.service.impl

import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.PinNotFoundException
import com.sorsix.pinterestclone.repository.PinJpaRepository
import com.sorsix.pinterestclone.service.PinService
import com.sorsix.pinterestclone.service.UserService
import org.springframework.stereotype.Service

@Service
class PinServiceImplementation(val repository: PinJpaRepository, val userService: UserService) : PinService {

    override fun findById(id: Long): Pin {
        return repository.findById(id)
            .orElseThrow { PinNotFoundException(String.format("Pin with id %d is not found", id)) }
    }

    override fun findAll(): List<Pin> {
        return repository.findAll()
    }

    override fun addPin(url: String, description: String, username: String): Pin {
        val user: User = userService.findByUsername(username)
        val newPin: Pin = Pin(0, url, description, 0, user)
        return repository.save(newPin)
    }

    override fun deletePin(id: Long) {
        repository.deleteById(id)
    }

    override fun updatePin(id: Long, description: String): Pin {
        return repository.updatePin(id, description)
    }

    override fun increaseFavourites(id: Long) {
        repository.updateFavorites(id)
    }

    override fun findAllByUserId(username: String): List<Pin> {
        return repository.findAllByUserUsername(username)
    }
}