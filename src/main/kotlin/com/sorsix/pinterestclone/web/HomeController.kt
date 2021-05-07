package com.sorsix.pinterestclone.web

import com.sorsix.pinterestclone.domain.Pin
import com.sorsix.pinterestclone.service.PinService
import com.sorsix.pinterestclone.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


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

}