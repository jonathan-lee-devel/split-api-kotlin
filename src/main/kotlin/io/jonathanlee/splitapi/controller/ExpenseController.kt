package io.jonathanlee.splitapi.controller

import io.jonathanlee.splitapi.dto.ExpenseDto
import io.jonathanlee.splitapi.form.ExpenseForm
import io.jonathanlee.splitapi.service.ExpenseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/expense")
class ExpenseController(
    private val expenseService: ExpenseService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getExpenses(): ResponseEntity<Collection<ExpenseDto>> {
        return ResponseEntity.ok(this.expenseService.getExpenses())
    }

    @GetMapping("/id/{expenseId}")
    @ResponseStatus(HttpStatus.OK)
    fun getExpenseByExpenseId(@PathVariable expenseId: String): ResponseEntity<ExpenseDto> {
        val expense = this.expenseService.getExpenseByExpenseId(expenseId)
        return if (expense.isEmpty)
            ResponseEntity.notFound().build()
        else
            ResponseEntity.ok(expense.get())
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createExpense(@RequestBody expenseForm: ExpenseForm): ResponseEntity<ExpenseDto> {
        val expense = this.expenseService.createExpense(expenseForm)
        return if (expense.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(expense.get())
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateExpense(@RequestBody expenseDto: ExpenseDto): ResponseEntity<ExpenseDto> {
        val expense = this.expenseService.updateExpense(expenseDto)
        return if (expense.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(expense.get())
    }

    @DeleteMapping("/id/{expenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteExpenseByExpenseId(@PathVariable expenseId: String): ResponseEntity<ExpenseDto> {
        val expense = this.expenseService.deleteExpenseByExpenseId(expenseId)
        return if (expense.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(expense.get())
    }

}
