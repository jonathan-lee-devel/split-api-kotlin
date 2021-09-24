package io.jonathanlee.splitapi.service.impl

import io.jonathanlee.splitapi.dto.PropertyDto
import io.jonathanlee.splitapi.form.PropertyForm
import io.jonathanlee.splitapi.model.Property
import io.jonathanlee.splitapi.repository.PropertyRepository
import io.jonathanlee.splitapi.repository.auth.UserRepository
import io.jonathanlee.splitapi.service.PropertyService
import org.springframework.stereotype.Service
import java.util.*

@Service
class PropertyServiceImpl(
    private val propertyRepository: PropertyRepository,
    private val userRepository: UserRepository
) : PropertyService {

    override fun getProperties(): Collection<PropertyDto> {
        return this.propertyRepository.findAll().map { PropertyDto(it) }
    }

    override fun getPropertyByPropertyId(propertyId: String): Optional<PropertyDto> {
        val property = this.propertyRepository.findByPropertyId(propertyId)
        return if (property == null)
            Optional.empty()
        else
            Optional.of(PropertyDto(property))
    }

    override fun createProperty(propertyForm: PropertyForm): Optional<PropertyDto> {
        val user = this.userRepository.findByUserId(propertyForm.userId)
        if (user == null)
            return Optional.empty()

        val property = Property(
            0L,
            UUID.randomUUID().toString(),
            Date(),
            propertyForm.name,
            propertyForm.address,
            user
        )
        this.propertyRepository.save(property)

        return Optional.of(PropertyDto(property))
    }

    override fun updateProperty(propertyDto: PropertyDto): Optional<PropertyDto> {
        val property = this.propertyRepository.findByPropertyId(propertyDto.propertyId)
        if (property == null)
            return Optional.empty()

        property.name = propertyDto.name
        property.address = propertyDto.address

        return Optional.of(PropertyDto(this.propertyRepository.save(property)))
    }

    override fun deletePropertyByPropertyId(propertyId: String): Optional<PropertyDto> {
        val property = this.propertyRepository.findByPropertyId(propertyId)
        if (property == null)
            return Optional.empty()

        this.propertyRepository.delete(property)

        return Optional.of(PropertyDto(property))
    }

}
