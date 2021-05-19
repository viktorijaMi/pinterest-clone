package com.sorsix.pinterestclone.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class InvalidCredentialsException(message: String?) : RuntimeException(message) {
}