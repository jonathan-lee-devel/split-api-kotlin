package io.jonathanlee.splitapi.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.jonathanlee.splitapi.model.Expense
import java.math.BigDecimal
import java.util.*

data class ExpenseDto(
    @JsonProperty("expense_id") val expenseId: String,
    @JsonProperty("created_at") val createdAt: Date,
    val amount: BigDecimal,
    val frequency: Expense.ExpenseFrequency,
    @JsonProperty("is_active") val isActive: Boolean,
    @JsonProperty("start_date") val startDate: Date,
    @JsonProperty("property_id") val propertyId: String
) {
    constructor(expense: Expense) : this(
        expense.expenseId,
        expense.createdAt,
        expense.amount,
        expense.frequency,
        expense.isActive,
        expense.startDate,
        expense.property.propertyId
    )
}
