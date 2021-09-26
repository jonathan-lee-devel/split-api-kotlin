package io.jonathanlee.splitapi.form.auth

import com.fasterxml.jackson.annotation.JsonProperty
import io.jonathanlee.splitapi.form.Form
import javax.validation.constraints.Email

/**
 * Form filled in with user-entered data representing a user to be registered.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
class UserRegistrationForm(
    @field:Email val email: String,
    val password: String,
    @JsonProperty("confirm_password") private val confirmPassword: String
) : Form {

    /**
     * Method required by forms to validate user-entered data.
     *
     * @return Boolean indicating if user-entered data is valid.
     */
    override fun validate(): Boolean {
        return password == confirmPassword
    }

}
