package io.jonathanlee.splitapi.repository

import io.jonathanlee.splitapi.model.Expense
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.Nullable

/**
 * Repository interface used to interact with expense entities.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface ExpenseRepository : JpaRepository<Expense, Long> {

    @Nullable
    fun findByExpenseId(expenseId: String): Expense

}
