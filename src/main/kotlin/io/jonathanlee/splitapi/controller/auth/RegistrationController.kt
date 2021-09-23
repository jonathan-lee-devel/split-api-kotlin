package io.jonathanlee.splitapi.controller.auth

import io.jonathanlee.splitapi.form.auth.UserRegistrationForm
import io.jonathanlee.splitapi.model.auth.User
import io.jonathanlee.splitapi.service.auth.RegistrationService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/register")
class RegistrationController(private val registrationService: RegistrationService) {

    @PostMapping
    fun register(@Valid @RequestBody userRegistrationForm: UserRegistrationForm): ResponseEntity<User> {
        println(userRegistrationForm.email)
        return ResponseEntity.ok(this.registrationService.registerNewUser(userRegistrationForm))
    }

    @GetMapping("/confirm")
    fun confirm(@RequestParam token: String): ResponseEntity<User> {
        return ResponseEntity.ok(this.registrationService.confirmNewUser(token))
    }

}
