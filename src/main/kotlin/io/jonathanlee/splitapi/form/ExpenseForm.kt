package io.jonathanlee.splitapi.form

import com.fasterxml.jackson.annotation.JsonProperty
import io.jonathanlee.splitapi.model.Expense
import java.math.BigDecimal
import java.util.*

/**
 * Form filled in with user-entered data representing an expense.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
class ExpenseForm(
    val amount: BigDecimal,
    val frequency: Expense.ExpenseFrequency,
    @JsonProperty("is_active") val isActive: Boolean,
    @JsonProperty("start_date") val startDate: Date,
    @JsonProperty("property_id") val propertyId: String
) : Form {

    override fun validate(): Boolean {
        return amount != null && frequency != null &&
                isActive != null && startDate != null
    }

}
