package com.sorsix.pinterestclone.web

import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.service.PinService
import com.sorsix.pinterestclone.service.UserService
import com.sorsix.pinterestclone.web.dto.PinDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/home")
class HomeController(
    val pinService: PinService,
    val userService: UserService
) {

    @GetMapping
    fun listAllPins(): List<Pin> {
        return pinService.findAll();
    }

    @GetMapping("/{id}")
    fun getPin(@PathVariable id: Long): Pin {
        return this.pinService.findById(id)
    }

    @PostMapping("/add")
    fun addPin(@RequestBody pinDto: PinDto): ResponseEntity<Pin> {
        return this.pinService.save(pinDto).let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping("/add/{id}")
    fun updatePin(@PathVariable id: Long, @RequestParam description: String){
        return this.pinService.updatePin(id, description)
    }

    @PostMapping("/favorites/{id}")
    fun increaseFavorites(@PathVariable id: Long) {
        return this.pinService.increaseFavourites(id)
    }

    @PostMapping("/delete/{id}")
    fun deletePin(@PathVariable id: Long) {
        this.pinService.deletePin(id)
    }
}