package io.jonathanlee.splitapi.form

import com.fasterxml.jackson.annotation.JsonProperty

class PropertyForm(
    val name: String,
    val address: String,
    @JsonProperty("user_id") val userId: String
) : Form {

    override fun validate(): Boolean {
        return this.name != null && this.address != null && userId != null
    }

}
