package io.jonathanlee.splitapi.controller

import io.jonathanlee.splitapi.dto.ExpenseDto
import io.jonathanlee.splitapi.form.ExpenseForm
import io.jonathanlee.splitapi.service.ExpenseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * REST controller used for expense interactions.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@RestController
@RequestMapping("/expense")
class ExpenseController(
    private val expenseService: ExpenseService
) {

    /**
     * GET request used to obtain all available expenses.
     *
     * @return Collection of expense information via ExpenseDto contained in a ResponseEntity.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getExpenses(): ResponseEntity<Collection<ExpenseDto>> {
        return ResponseEntity.ok(this.expenseService.getExpenses())
    }

    /**
     * GET request used to obtain specified expense.
     *
     * @param expenseId expense ID of expense information to be obtained.
     * @return expense information of specified expense via ExpenseDto contained in a ResponseEntity.
     */
    @GetMapping("/id/{expenseId}")
    @ResponseStatus(HttpStatus.OK)
    fun getExpenseByExpenseId(@PathVariable expenseId: String): ResponseEntity<ExpenseDto> {
        val expense = this.expenseService.getExpenseByExpenseId(expenseId)
        return if (expense.isEmpty)
            ResponseEntity.notFound().build()
        else
            ResponseEntity.ok(expense.get())
    }

    /**
     * POST request used to create a new expense.
     *
     * @param expenseForm form data used to create new expense.
     * @return expense data contained in an ExpenseDto.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createExpense(@RequestBody expenseForm: ExpenseForm): ResponseEntity<ExpenseDto> {
        val expense = this.expenseService.createExpense(expenseForm)
        return if (expense.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(expense.get())
    }

    /**
     * PUT request used to update an expense.
     *
     * @param expenseDto DTO containing updated expense information.
     * @return expense data contained in an ExpenseDto.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateExpense(@RequestBody expenseDto: ExpenseDto): ResponseEntity<ExpenseDto> {
        val expense = this.expenseService.updateExpense(expenseDto)
        return if (expense.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(expense.get())
    }

    /**
     * DELETE request used to delete an expense.
     *
     * @param expenseId ID of expense to be deleted.
     * @return expense data contained in an ExpenseDto.
     */
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
