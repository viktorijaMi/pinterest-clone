package com.sorsix.pinterestclone.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class PasswordsDoNotMatchException(message: String?) : RuntimeException(message) {
}