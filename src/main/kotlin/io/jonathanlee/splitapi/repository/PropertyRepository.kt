package io.jonathanlee.splitapi.repository

import io.jonathanlee.splitapi.model.Property
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.Nullable

/**
 * Repository interface used to interact with property entities.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface PropertyRepository : JpaRepository<Property, Long> {

    @Nullable
    fun findByPropertyId(propertyId: String): Property

}
