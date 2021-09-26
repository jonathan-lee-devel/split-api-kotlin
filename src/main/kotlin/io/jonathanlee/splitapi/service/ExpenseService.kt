package io.jonathanlee.splitapi.service

import io.jonathanlee.splitapi.dto.ExpenseDto
import io.jonathanlee.splitapi.form.ExpenseForm
import java.util.*

/**
 * Expense service used to manage expenses.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface ExpenseService {

    /**
     * Method used to obtain available expenses.
     *
     * @return expense data contained in ExpenseDto.
     */
    fun getExpenses(): Collection<ExpenseDto>

    /**
     * Method used to obtain a specific expense by expense ID.
     *
     * @param expenseId ID of specific expense to be obtained.
     * @return expense data contained in an ExpenseDto.
     */
    fun getExpenseByExpenseId(expenseId: String): Optional<ExpenseDto>

    /**
     * Method used to create an expense.
     *
     * @param expenseForm form data used to create an expense.
     * @return expense data contained in an ExpenseDto.
     */
    fun createExpense(expenseForm: ExpenseForm): Optional<ExpenseDto>

    /**
     * Method used to update an expense.
     *
     * @param expenseDto expense data for updated expense.
     * @return expense data contained in an ExpenseDto
     */
    fun updateExpense(expenseDto: ExpenseDto): Optional<ExpenseDto>

    /**
     * Method used to delete an expense.
     *
     * @param expenseId ID of expense to be deleted.
     * @return expense data contained in an ExpenseDto.
     */
    fun deleteExpenseByExpenseId(expenseId: String): Optional<ExpenseDto>

}
