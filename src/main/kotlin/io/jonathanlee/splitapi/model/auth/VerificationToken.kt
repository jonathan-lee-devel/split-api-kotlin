package io.jonathanlee.splitapi.model.auth

import java.util.*
import javax.persistence.*

@Entity
data class VerificationToken(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    val token: String,
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id") val user: User,
    @Column(name = "expiry_date") val expiryDate: Date
)
