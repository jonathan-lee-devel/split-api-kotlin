package io.jonathanlee.splitapi.service.auth

import io.jonathanlee.splitapi.form.auth.UserRegistrationForm
import io.jonathanlee.splitapi.model.auth.User

interface RegistrationService {

    fun registerNewUser(userRegistrationForm: UserRegistrationForm): User

    fun confirmNewUser(token: String): User

}
