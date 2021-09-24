package io.jonathanlee.splitapi.form

class PropertyForm(
    val name: String,
    val address: String,
    val userId: String
) : Form {

    override fun validate(): Boolean {
        return this.name != null && this.address != null && userId != null
    }

}
