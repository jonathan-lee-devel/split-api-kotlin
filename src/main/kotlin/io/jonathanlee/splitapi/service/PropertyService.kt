package io.jonathanlee.splitapi.service

import io.jonathanlee.splitapi.dto.PropertyDto
import io.jonathanlee.splitapi.form.PropertyForm
import java.util.*

interface PropertyService {

    fun getProperties(): Collection<PropertyDto>

    fun getPropertyByPropertyId(propertyId: String): Optional<PropertyDto>

    fun createProperty(propertyForm: PropertyForm): Optional<PropertyDto>

    fun updateProperty(propertyDto: PropertyDto): Optional<PropertyDto>

    fun deletePropertyByPropertyId(propertyId: String): Optional<PropertyDto>

}
