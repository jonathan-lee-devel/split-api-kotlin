package io.jonathanlee.splitapi.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class IndexController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getIndex(
        @RequestParam("session_state") sessionState: String,
        @RequestParam code: String
    ): ResponseEntity<String> {
        return ResponseEntity.ok("Index (Login Successful)")
    }

}
