package com.sorsix.pinterestclone.service

import com.sorsix.pinterestclone.domain.Favorite
import com.sorsix.pinterestclone.domain.FavoriteId
import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.PinNotFoundException
import com.sorsix.pinterestclone.exceptions.UserNotFoundException
import com.sorsix.pinterestclone.web.dto.PinDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows


@SpringBootTest
class FavoriteServiceTest(@Autowired val favoriteService: FavoriteService,
                          @Autowired val userService: UserService,
                          @Autowired val pinService: PinService) {

    @BeforeEach
    fun init() {
        userService.saveAuthenticatedUser(0, "username", "avatarUrl")
        pinService.savePin(PinDto("desc", "url"), 0)
    }

    @Test
    fun `save favorite`() {
        val user = User(0, "username", "avatarUrl")
        val pin = Pin(1, "url", "desc", 0, mutableListOf(), user)
        val favorite = Favorite(FavoriteId(pin.id, user.id), pin, user)
        assertEquals(favorite, favoriteService.updateFavorite(pin.id, user.id))
    }

    @Test
    fun `trying to save favorite with pin that doesn't exist`() {
        assertThrows<PinNotFoundException> { this.favoriteService.updateFavorite(121, 0) }
    }

    @Test
    fun `trying to save favorite with user that doesn't exist`() {
        assertThrows<UserNotFoundException> { this.favoriteService.updateFavorite(1, 121) }
    }

    @Test
    fun `trying to get favorite by pin id`() {
        val user = User(0, "username", "avatarUrl")
        val pin = Pin(1, "url", "desc", 0, mutableListOf(), user)
        val favorite: Favorite = this.favoriteService.updateFavorite(pin.id, user.id)
        assertEquals(favorite.id, this.favoriteService.findByPinId(pin.id).id)
    }
}