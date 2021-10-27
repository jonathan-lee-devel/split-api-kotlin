package io.jonathanlee.splitapi.controller

import io.jonathanlee.splitapi.dto.PropertyDto
import io.jonathanlee.splitapi.form.PropertyForm
import io.jonathanlee.splitapi.interceptor.auth.AuthorizationInterceptor
import io.jonathanlee.splitapi.service.PropertyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * REST controller used for property interactions.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@RestController
@RequestMapping("/property")
class PropertyController(
    private val propertyService: PropertyService
) {

    /**
     * GET request used to obtain all available properties.
     *
     * @return Collection of property information via PropertyDto contained in a ResponseEntity.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getProperties(): ResponseEntity<Collection<PropertyDto>> {
        return ResponseEntity.ok(this.propertyService.getProperties())
    }

    /**
     * GET request used to obtain specified property.
     *
     * @param propertyId property ID of property information to be exposed.
     * @return property information of specified property via PropertyDto contained in a ResponseEntity.
     */
    @GetMapping("/id/{propertyId}")
    @ResponseStatus(HttpStatus.OK)
    fun getPropertyByPropertyId(@PathVariable propertyId: String): ResponseEntity<PropertyDto> {
        val property = this.propertyService.getPropertyByPropertyId(propertyId)
        return if (property.isEmpty)
            ResponseEntity.notFound().build()
        else
            ResponseEntity.ok(property.get())
    }

    /**
     * POST request used to create a new property.
     *
     * @param propertyForm form data used to create new property.
     * @return property data contained in a PropertyDto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProperty(
        @RequestAttribute(AuthorizationInterceptor.usernameAttribute) username: String,
        @RequestBody propertyForm: PropertyForm
    ): ResponseEntity<PropertyDto> {
        val property = this.propertyService.createProperty(username, propertyForm)
        return if (property.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(property.get())
    }

    /**
     * PUT request used to update a property.
     *
     * @param propertyDto DT containing updated property information.
     * @return property data contained in a PropertyDto.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateProperty(@RequestBody propertyDto: PropertyDto): ResponseEntity<PropertyDto> {
        val property = this.propertyService.updateProperty(propertyDto)
        return if (property.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(property.get())
    }

    /**
     * DELETE request used to delete a property.
     *
     * @param propertyId ID of property to be deleted.
     * @return property data contained in a PropertyDto.
     */
    @DeleteMapping("/id/{propertyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePropertyByPropertyId(@PathVariable propertyId: String): ResponseEntity<PropertyDto> {
        val property = this.propertyService.deletePropertyByPropertyId(propertyId)
        return if (property.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(property.get())
    }

}
