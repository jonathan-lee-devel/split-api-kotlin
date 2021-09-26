package io.jonathanlee.splitapi.form

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Form filled in with user-entered data representing a renter.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
class RenterForm(
    val name: String,
    @JsonProperty("user_id") val userId: String
) : Form {

    override fun validate(): Boolean {
        return this.name != null && this.userId != null
    }

}
