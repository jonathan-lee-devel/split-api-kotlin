package io.jonathanlee.splitapi.service

import io.jonathanlee.splitapi.dto.ExpenseDto
import io.jonathanlee.splitapi.form.ExpenseForm
import java.util.*

interface ExpenseService {

    fun getExpenses(): Collection<ExpenseDto>

    fun getExpenseByExpenseId(expenseId: String): Optional<ExpenseDto>

    fun createExpense(expenseForm: ExpenseForm): Optional<ExpenseDto>

    fun updateExpense(expenseDto: ExpenseDto): Optional<ExpenseDto>

    fun deleteExpenseByExpenseId(expenseId: String): Optional<ExpenseDto>

}
