package io.jonathanlee.splitapi.service.impl

import io.jonathanlee.splitapi.dto.PropertyDto
import io.jonathanlee.splitapi.form.PropertyForm
import io.jonathanlee.splitapi.model.Property
import io.jonathanlee.splitapi.repository.PropertyRepository
import io.jonathanlee.splitapi.service.PropertyService
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestAttribute
import java.util.*

/**
 * Implementation of property service used to manage properties.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Service
class PropertyServiceImpl(
    private val propertyRepository: PropertyRepository,
) : PropertyService {

    /**
     * Method used to obtain available properties.
     *
     * @return property data contained in PropertyDto.
     */
    override fun getProperties(): Collection<PropertyDto> {
        return this.propertyRepository.findAll().map { PropertyDto(it) }
    }

    /**
     * Method used to obtain a specific property by property ID.
     *
     * @param propertyId ID of specific property to be obtained.
     * @return property data contained in a PropertyDto.
     */
    override fun getPropertyByPropertyId(propertyId: String): Optional<PropertyDto> {
        val property = this.propertyRepository.findByPropertyId(propertyId)
        return if (property == null)
            Optional.empty()
        else
            Optional.of(PropertyDto(property))
    }

    /**
     * Method used to create a property.
     *
     * @param propertyForm form data used to create a property.
     * @return property data contained in a PropertyDto.
     */
    override fun createProperty(@RequestAttribute username: String, propertyForm: PropertyForm): Optional<PropertyDto> {
        val property = Property(
            0L,
            UUID.randomUUID().toString(),
            Date(),
            propertyForm.name,
            propertyForm.address,
            username
        )
        this.propertyRepository.save(property)

        return Optional.of(PropertyDto(property))
    }

    /**
     * Method used to update a property.
     *
     * @param propertyDto property data for updated property.
     * @return property data contained in a PropertyDto.
     */
    override fun updateProperty(propertyDto: PropertyDto): Optional<PropertyDto> {
        val property = this.propertyRepository.findByPropertyId(propertyDto.propertyId)
        if (property == null)
            return Optional.empty()

        property.name = propertyDto.name
        property.address = propertyDto.address

        return Optional.of(PropertyDto(this.propertyRepository.save(property)))
    }

    /**
     * Method used to delete a property.
     *
     * @param propertyId ID of property to be deleted.
     * @return property data contained in a PropertyDto.
     */
    override fun deletePropertyByPropertyId(propertyId: String): Optional<PropertyDto> {
        val property = this.propertyRepository.findByPropertyId(propertyId)
        if (property == null)
            return Optional.empty()

        this.propertyRepository.delete(property)

        return Optional.of(PropertyDto(property))
    }

}
