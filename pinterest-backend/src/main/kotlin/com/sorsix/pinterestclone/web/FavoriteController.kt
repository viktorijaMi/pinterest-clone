package com.sorsix.pinterestclone.web

import com.sorsix.pinterestclone.domain.Favorite
import com.sorsix.pinterestclone.exceptions.FavoriteNotFoundException
import com.sorsix.pinterestclone.exceptions.PinNotFoundException
import com.sorsix.pinterestclone.service.FavoriteService
import com.sorsix.pinterestclone.service.PinService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/favorite")
class FavoriteController(
    val pinService: PinService,
    val favoriteService: FavoriteService
) {

    @GetMapping("/{id}")
    fun getAllByPinId(@PathVariable id: Long): List<Favorite> {
        return this.favoriteService.findAllByPinId(id)
    }

    /**
     * Error handlers
     * */
    @ExceptionHandler(FavoriteNotFoundException::class)
    fun pinExceptionHandler(exception: FavoriteNotFoundException): Map<String, String> {
        return mapOf("error" to exception.toString())
    }
}