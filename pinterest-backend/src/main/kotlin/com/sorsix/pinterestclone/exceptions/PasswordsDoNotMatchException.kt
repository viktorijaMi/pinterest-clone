package com.sorsix.pinterestclone.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class PasswordsDoNotMatchException(message: String?) : RuntimeException(message) {
}