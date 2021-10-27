package io.jonathanlee.splitapi.form

/**
 * Form filled in with user-entered data representing an property.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
class PropertyForm(
    val name: String,
    val address: String
) : Form {

    override fun validate(): Boolean {
        return this.name != null && this.address != null
    }

}
