package io.jonathanlee.splitapi.form

import com.fasterxml.jackson.annotation.JsonProperty

class RenterForm(
    val name: String,
    @JsonProperty("user_id") val userId: String
) : Form {

    override fun validate(): Boolean {
        return this.name != null && this.userId != null
    }

}
