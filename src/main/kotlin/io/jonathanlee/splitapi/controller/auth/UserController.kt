package io.jonathanlee.splitapi.controller.auth

import io.jonathanlee.splitapi.dto.auth.UserDto
import io.jonathanlee.splitapi.form.auth.PasswordResetForm
import io.jonathanlee.splitapi.service.auth.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getUsers(): ResponseEntity<Collection<UserDto>> {
        return ResponseEntity.ok(this.userService.getUsers())
    }

    /**
     * GET request used to obtain specified user.
     *
     * @param email email of user information to be obtained.
     * @return user information of specified user via UserDto contained in a ResponseEntity.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserByEmail(@PathVariable email: String): ResponseEntity<UserDto> {
        val user = this.userService.getUserByEmail(email)
        return if (user.isEmpty)
            ResponseEntity.notFound().build()
        else
            return ResponseEntity.ok(user.get())
    }

    /**
     * GET request used to obtain specified user.
     *
     * @param userId user ID of user information to be obtained.
     * @return user information of specified user via UserDto contained in a ResponseEntity.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/id/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun getByUserId(@PathVariable userId: String): ResponseEntity<UserDto> {
        val user = this.userService.getUserByUserId(userId)
        return if (user.isEmpty)
            ResponseEntity.notFound().build()
        else
            ResponseEntity.ok(user.get())
    }

    /**
     * POST request used to request password reset for given account.
     *
     * @param email email of user account for which password is to be reset.
     * @return user data contained in UserDto.
     */
    @PostMapping("/resetPassword")
    @ResponseStatus(HttpStatus.OK)
    fun resetPassword(@RequestParam email: String): ResponseEntity<UserDto> {
        this.userService.resetPasswordByEmail(email)

        // Always return the same response to avoid scraping for valid emails/users
        return ResponseEntity.ok().build()
    }

    /**
     * POST request for confirming password reset request for given account.
     *
     * @param tokenContents contents of password reset token used to verify password reset request.
     * @param passwordResetForm password reset form data used to reset password.
     * @return user data contained in UserDto.
     */
    @PostMapping("/resetPassword/confirm")
    @ResponseStatus(HttpStatus.OK)
    fun confirmResetPassword(
        @RequestParam("token") tokenContents: String,
        @RequestBody passwordResetForm: PasswordResetForm
    ): ResponseEntity<UserDto> {
        val user = this.userService.confirmPasswordReset(tokenContents, passwordResetForm)
        return if (user.isEmpty)
            ResponseEntity.notFound().build()
        else
            ResponseEntity.ok(user.get())
    }

}
