package io.jonathanlee.splitapi.form.auth

import com.fasterxml.jackson.annotation.JsonProperty
import io.jonathanlee.splitapi.form.Form
import javax.validation.constraints.Email

class UserRegistrationForm(
    @JsonProperty("firstname") val firstName: String,
    @JsonProperty("lastname") val lastName: String,
    @field:Email val email: String,
    val username: String,
    val password: String,
    @JsonProperty("confirm_password") val confirmPassword: String
) : Form {

    override fun validate(): Boolean {
        return this.password == this.confirmPassword
    }

}