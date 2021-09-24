package io.jonathanlee.splitapi.form.auth

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
    private val confirmPassword: String
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
