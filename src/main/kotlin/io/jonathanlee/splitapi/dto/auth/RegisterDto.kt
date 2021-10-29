package io.jonathanlee.splitapi.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty
import io.jonathanlee.splitapi.form.Form

data class RegisterDto(
    @JsonProperty("firstname") val firstName: String,
    @JsonProperty("lastname") val lastName: String,
    val email: String,
    val password: String,
    @JsonProperty("confirm_password") val confirmPassword: String
) : Form {
    override fun validate(): Boolean {
        return this.password == this.confirmPassword
    }
}
