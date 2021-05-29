package com.sorsix.pinterestclone.web

import com.sorsix.pinterestclone.domain.Favorite
import com.sorsix.pinterestclone.exceptions.FavoriteNotFoundException
import com.sorsix.pinterestclone.service.FavoriteService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/favorites")
class FavoriteController(
    val favoriteService: FavoriteService
) {

    @GetMapping("/{pinId}")
    fun getAllByPinId(@PathVariable pinId: Long): List<Favorite> {
        return this.favoriteService.findAllByPinId(pinId)
    }

    @PostMapping("/{pinId}")
    fun updateFavorite(
        @PathVariable pinId: Long,
        @AuthenticationPrincipal principal: OAuth2User
    ) {
        val userId: Int = principal.attributes["id"] as Int
        this.favoriteService.updateFavorite(pinId, userId)
    }

    /**
     * Error handlers
     * */
    @ExceptionHandler(FavoriteNotFoundException::class)
    fun pinExceptionHandler(exception: FavoriteNotFoundException): Map<String, String> {
        return mapOf("error" to exception.toString())
    }
}