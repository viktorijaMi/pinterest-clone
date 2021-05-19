package com.sorsix.pinterestclone.web

import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.exceptions.PinNotFoundException
import com.sorsix.pinterestclone.service.FavoriteService
import com.sorsix.pinterestclone.service.PinService
import com.sorsix.pinterestclone.web.dto.PinDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/pins")
class PinController(
    val pinService: PinService,
    val favoriteService: FavoriteService
) {

    @GetMapping()
    fun listAllPins(): List<Pin> {
        return pinService.findAll();
    }

    @GetMapping("/{id}")
    fun getPin(@PathVariable id: Long): ResponseEntity<Pin> {
        return this.pinService.findById(id).let {
            ResponseEntity.ok(it)
        }
    }

    @PostMapping("/add")
    fun savePin(@RequestBody pinDto: PinDto): ResponseEntity<Pin> {
        return this.pinService.savePin(pinDto).let {
            ResponseEntity.ok(it)
        }
    }

    @PostMapping("/add/{id}")
    fun updatePin(@PathVariable id: Long, @RequestParam description: String) {
        this.pinService.updatePin(id, description)
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