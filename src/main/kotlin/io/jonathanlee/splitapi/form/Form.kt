package io.jonathanlee.splitapi.form

/**
 * Interface used to represent a form filled in using user-entered data.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface Form {

    /**
     * Abstract method required by forms to validate user-entered data.
     *
     * @return Boolean indicating if user-entered data is valid.
     */
    fun validate(): Boolean

}
