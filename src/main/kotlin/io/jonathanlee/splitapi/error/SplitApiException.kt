package io.jonathanlee.splitapi.error

import org.springframework.http.HttpStatus

class SplitApiException(val status: HttpStatus, override val message: String) : Throwable()
