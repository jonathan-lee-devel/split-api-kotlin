package io.jonathanlee.splitapi.service.impl

import io.jonathanlee.splitapi.dto.ExpenseDto
import io.jonathanlee.splitapi.form.ExpenseForm
import io.jonathanlee.splitapi.model.Expense
import io.jonathanlee.splitapi.repository.ExpenseRepository
import io.jonathanlee.splitapi.repository.PropertyRepository
import io.jonathanlee.splitapi.service.ExpenseService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExpenseServiceImpl(
    private val expenseRepository: ExpenseRepository,
    private val propertyRepository: PropertyRepository
) : ExpenseService {

    override fun getExpenses(): Collection<ExpenseDto> {
        return this.expenseRepository.findAll().map { ExpenseDto(it) }
    }

    override fun getExpenseByExpenseId(expenseId: String): Optional<ExpenseDto> {
        val expense = this.expenseRepository.findByExpenseId(expenseId)
        return if (expense == null)
            Optional.empty()
        else
            Optional.of(ExpenseDto(expense))
    }

    override fun createExpense(expenseForm: ExpenseForm): Optional<ExpenseDto> {
        val property = this.propertyRepository.findByPropertyId(expenseForm.propertyId)
        if (property == null)
            return Optional.empty()

        val expense = Expense(
            0L,
            UUID.randomUUID().toString(),
            Date(),
            expenseForm.amount,
            expenseForm.frequency,
            expenseForm.isActive,
            expenseForm.startDate,
            property
        )
        this.expenseRepository.save(expense)

        return Optional.of(ExpenseDto(expense))
    }

    override fun updateExpense(expenseDto: ExpenseDto): Optional<ExpenseDto> {
        val expense = this.expenseRepository.findByExpenseId(expenseDto.expenseId)
        if (expense == null)
            return Optional.empty()

        expense.amount = expenseDto.amount
        expense.frequency = expenseDto.frequency
        expense.isActive = expenseDto.isActive
        expense.startDate = expenseDto.startDate

        return Optional.of(ExpenseDto(this.expenseRepository.save(expense)))
    }

    override fun deleteExpenseByExpenseId(expenseId: String): Optional<ExpenseDto> {
        val expense = this.expenseRepository.findByExpenseId(expenseId)
        if (expense == null)
            return Optional.empty()

        this.expenseRepository.delete(expense)

        return Optional.of(ExpenseDto(expense))
    }

}