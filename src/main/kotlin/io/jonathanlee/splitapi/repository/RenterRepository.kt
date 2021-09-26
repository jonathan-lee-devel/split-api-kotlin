package io.jonathanlee.splitapi.repository

import io.jonathanlee.splitapi.model.Renter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.Nullable

interface RenterRepository : JpaRepository<Renter, Long> {

    @Nullable
    fun findByRenterId(renterId: String): Renter

}
