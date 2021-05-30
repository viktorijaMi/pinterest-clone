package com.sorsix.pinterestclone.service

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
class PinServiceTest(@Autowired val pinService: PinService,
                    @Autowired val userService: UserService) {

    @BeforeEach
    fun init() {
        userService.saveAuthenticatedUser(0, "username", "avatarUrl")
    }

    @Test
    fun `trying to save new pin`(){
        val pinDto = PinDto("desc", "url")
        val user = User(0, "username","avatarUrl")
        val pin = Pin(1, "url", "desc", 0, mutableListOf(), user)
        assertEquals(pin.id, this.pinService.savePin(pinDto, 0).id)
    }

    @Test
    fun `trying to save new pin with user that doesn't exist`() {
        val pinDto = PinDto("desc", "url")
        assertThrows<UserNotFoundException>{ this.pinService.savePin(pinDto, 2)}
    }

    @Test
    fun `testing the all pins method`(){
        val pinDto1 = PinDto("desc1", "url1")
        val pinDto2 = PinDto("desc2", "url2")
        this.pinService.savePin(pinDto1, 0)
        this.pinService.savePin(pinDto2, 0)

        assertEquals(3, this.pinService.findAll().size)
    }

    @Test
    fun `trying to find pin by given id`() {
        val pinDto = PinDto("desc", "url")
        val pin: Pin = this.pinService.savePin(pinDto, 0)
        assertEquals(pin.id,this.pinService.findById(1).id)
    }

    @Test
    fun `trying to find pin that doesn't exist`() {
        assertThrows<PinNotFoundException> { this.pinService.findById(321) }
    }

    @Test
    fun `trying to find pin by given createdBy id`() {
        val pinDto = PinDto("desc", "url")
        val pin: Pin = this.pinService.savePin(pinDto, 0)
        assertEquals(pin.id, this.pinService.findAllByCreatedById(0).last().id)
    }
}