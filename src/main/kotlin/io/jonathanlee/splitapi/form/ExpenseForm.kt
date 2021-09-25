package io.jonathanlee.splitapi.form

import io.jonathanlee.splitapi.model.Expense
import java.math.BigDecimal
import java.util.*

class ExpenseForm(
    val amount: BigDecimal,
    val frequency: Expense.ExpenseFrequency,
    val isActive: Boolean,
    val startDate: Date,
    val propertyId: String
) : Form {

    override fun validate(): Boolean {
        return amount != null && frequency != null &&
                isActive != null && startDate != null
    }

}
