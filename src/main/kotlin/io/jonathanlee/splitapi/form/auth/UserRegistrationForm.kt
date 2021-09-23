package io.jonathanlee.splitapi.form.auth

import io.jonathanlee.splitapi.form.Form
import javax.validation.constraints.Email

class UserRegistrationForm(
    @field:Email val email: String,
    val password: String,
    private val confirmPassword: String
) : Form {

    override fun validate(): Boolean {
        return password == confirmPassword
    }

}
