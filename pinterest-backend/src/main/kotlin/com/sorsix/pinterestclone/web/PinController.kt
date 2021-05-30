package com.sorsix.pinterestclone.web

import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.domain.User
import com.sorsix.pinterestclone.exceptions.PinNotFoundException
import com.sorsix.pinterestclone.service.FavoriteService
import com.sorsix.pinterestclone.service.PinService
import com.sorsix.pinterestclone.web.dto.PinDto
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/api/pins")
class PinController(
    val pinService: PinService
) {

    @GetMapping("/all")
    fun listAllPins(): List<Pin> {
        return pinService.findAll()
    }

    @GetMapping("/my-pins")
    fun listMyPins(@AuthenticationPrincipal principal: OAuth2User): List<Pin> {
        val userId: Int = principal.attributes["id"] as Int
        return pinService.findAllByCreatedById(userId)
    }

    @GetMapping("/{id}")
    fun getPin(@PathVariable id: Long): ResponseEntity<Pin> {
        return this.pinService.findById(id).let {
            ResponseEntity.ok(it)
        }
    }

    @PostMapping
    fun savePin(
        @RequestBody pinDto: PinDto,
        @AuthenticationPrincipal principal: OAuth2User
    ): ResponseEntity<Pin> {
        val userId: Int = principal.attributes["id"] as Int
        return this.pinService.savePin(pinDto, userId).let {
            ResponseEntity.ok(it)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deletePin(@PathVariable id: Long) {
        this.pinService.deletePin(id)
    }

    /**
     * Error handlers
     * */
    @ExceptionHandler(PinNotFoundException::class)
    fun pinExceptionHandler(exception: PinNotFoundException): Map<String, String> {
        return mapOf("error" to exception.toString())
    }

}