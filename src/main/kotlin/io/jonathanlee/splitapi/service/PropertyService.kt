package io.jonathanlee.splitapi.service

import io.jonathanlee.splitapi.dto.PropertyDto
import io.jonathanlee.splitapi.form.PropertyForm
import java.util.*

/**
 * Property service used to manage properties.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface PropertyService {

    /**
     * Method used to obtain available properties.
     *
     * @return property data contained in PropertyDto.
     */
    fun getProperties(): Collection<PropertyDto>

    /**
     * Method used to obtain a specific property by property ID.
     *
     * @param propertyId ID of specific property to be obtained.
     * @return property data contained in a PropertyDto.
     */
    fun getPropertyByPropertyId(propertyId: String): Optional<PropertyDto>

    /**
     * Method used to create a property.
     *
     * @param propertyForm form data used to create a property.
     * @return property data contained in a PropertyDto.
     */
    fun createProperty(propertyForm: PropertyForm): Optional<PropertyDto>

    /**
     * Method used to update a property.
     *
     * @param propertyDto property data for updated property.
     * @return property data contained in a PropertyDto.
     */
    fun updateProperty(propertyDto: PropertyDto): Optional<PropertyDto>

    /**
     * Method used to delete a property.
     *
     * @param propertyId ID of property to be deleted.
     * @return property data contained in a PropertyDto.
     */
    fun deletePropertyByPropertyId(propertyId: String): Optional<PropertyDto>

}
