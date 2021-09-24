package io.jonathanlee.splitapi.form.auth

import io.jonathanlee.splitapi.form.Form

class PasswordResetForm(
    val password: String,
    private val confirmPassword: String
) : Form {

    override fun validate(): Boolean {
        return this.password == this.confirmPassword
    }

}
