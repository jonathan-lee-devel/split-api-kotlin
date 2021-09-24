package io.jonathanlee.splitapi.controller.auth

import io.jonathanlee.splitapi.dto.auth.UserDto
import io.jonathanlee.splitapi.service.auth.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * REST controller used for user interactions.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    /**
     * GET request used to obtain all available users.
     *
     * @return Collection of user information via UserDto contained in a ResponseEntity.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getUsers(): ResponseEntity<Collection<UserDto>> {
        return ResponseEntity.ok(this.userService.getUsers())
    }

    /**
     * GET request used to obtain specified user.
     *
     * @param userId user ID of user information to be obtained.
     * @return user information of specified user via UserDto contained in a ResponseEntity.
     */
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun getByUserId(@PathVariable userId: String): ResponseEntity<UserDto> {
        return ResponseEntity.ok(this.userService.getUserByUserId(userId))
    }

}
