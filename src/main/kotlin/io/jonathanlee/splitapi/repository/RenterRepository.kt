package io.jonathanlee.splitapi.repository

import io.jonathanlee.splitapi.model.Renter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.Nullable

/**
 * Repository interface used to interact with renter entities.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface RenterRepository : JpaRepository<Renter, Long> {

    @Nullable
    fun findByRenterId(renterId: String): Renter

}
