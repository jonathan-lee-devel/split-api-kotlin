package io.jonathanlee.splitapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

/**
 * Entity used to represent an expense.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Entity
data class Expense(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @JsonProperty("expense_id") @Column(name = "expense_id", unique = true) val expenseId: String,
    @JsonProperty("created_at") @Column(name = "created_at") val createdAt: Date,
    var amount: BigDecimal,
    var frequency: ExpenseFrequency,
    @JsonProperty("is_active") @Column(name = "is_active") var isActive: Boolean,
    @JsonProperty("start_date") @Column(name = "start_date") var startDate: Date,
    @JsonIgnore @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        optional = false,
        targetEntity = Property::class
    ) val property: Property
) {
    enum class ExpenseFrequency {
        ONCE,
        DAILY,
        WEEKLY,
        BIWEEKLY,
        MONTHLY
    }
}
