package com.sorsix.pinterestclone.web

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.service.PinService
import com.sorsix.pinterestclone.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RestController
@RequestMapping("/api/home")
class HomeController(
    val pinService: PinService,
    val userService: UserService
) {

    @GetMapping
    fun listAllPins(): List<Pin> {
        println(pinService.findAll())
        return pinService.findAll()
    }

    @GetMapping("/{id}")
    fun getPin(@PathVariable id: Long): Pin {
        return this.pinService.findById(id)
    }

}