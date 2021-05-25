package com.sorsix.pinterestclone.web

import com.sorsix.pinterestclone.domain.Favorite
import com.sorsix.pinterestclone.exceptions.FavoriteNotFoundException
import com.sorsix.pinterestclone.exceptions.PinNotFoundException
import com.sorsix.pinterestclone.service.FavoriteService
import com.sorsix.pinterestclone.service.PinService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/favorites")
class FavoriteController(
    val pinService: PinService,
    val favoriteService: FavoriteService
) {

    @GetMapping("/{id}")
    fun getAllByPinId(@PathVariable id: Long): List<Favorite> {
        return this.favoriteService.findAllByPinId(id)
    }

    @PostMapping("/{pinId}")
    fun updateFavorite(
        @PathVariable pinId: Long,
        @AuthenticationPrincipal principal: OAuth2User
    ) {
        val username: String = principal.attributes["name"].toString()
        this.favoriteService.updateFavorite(pinId, username)
    }

    /**
     * Error handlers
     * */
    @ExceptionHandler(FavoriteNotFoundException::class)
    fun pinExceptionHandler(exception: FavoriteNotFoundException): Map<String, String> {
        return mapOf("error" to exception.toString())
    }
}