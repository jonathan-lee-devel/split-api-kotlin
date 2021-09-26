package io.jonathanlee.splitapi.form.auth

import io.jonathanlee.splitapi.form.Form

/**
 * Form filled in with user-entered data representing a password reset.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
class PasswordResetForm(
    val password: String,
    private val confirmPassword: String
) : Form {

    override fun validate(): Boolean {
        return this.password == this.confirmPassword
    }

}
