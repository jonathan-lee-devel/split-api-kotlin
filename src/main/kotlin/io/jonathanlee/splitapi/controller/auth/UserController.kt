package io.jonathanlee.splitapi.controller.auth

import io.jonathanlee.splitapi.dto.auth.UserDto
import io.jonathanlee.splitapi.service.auth.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getUsers(): ResponseEntity<Collection<UserDto>> {
        return ResponseEntity.ok(this.userService.getUsers())
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun getByUserId(@PathVariable userId: String): ResponseEntity<UserDto> {
        return ResponseEntity.ok(this.userService.getUserByUserId(userId))
    }

}
