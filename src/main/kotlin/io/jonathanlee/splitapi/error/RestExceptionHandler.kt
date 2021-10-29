package io.jonathanlee.splitapi.error

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(SplitApiException::class)
    fun handleSplitApiException(ex: SplitApiException): ResponseEntity<SplitApiError> {
        return ResponseEntity.status(ex.status).body(SplitApiError(Date(), ex.message.toString()))
    }

}
