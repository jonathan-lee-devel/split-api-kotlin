package io.jonathanlee.splitapi.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import java.net.URI

@Controller
@RequestMapping("/")
class IndexController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getIndex(
        @RequestParam("session_state") sessionState: String,
        @RequestParam code: String
    ): ResponseEntity<Any> {
        val url = URI("http://localhost:4200/login")
        val headers = HttpHeaders()
        headers.location = url
        return ResponseEntity(headers, HttpStatus.SEE_OTHER)
    }

}
