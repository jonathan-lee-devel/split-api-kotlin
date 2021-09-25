package io.jonathanlee.splitapi.controller

import io.jonathanlee.splitapi.dto.PropertyDto
import io.jonathanlee.splitapi.form.PropertyForm
import io.jonathanlee.splitapi.service.PropertyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/property")
class PropertyController(
    private val propertyService: PropertyService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getProperties(): ResponseEntity<Collection<PropertyDto>> {
        return ResponseEntity.ok(this.propertyService.getProperties())
    }

    @GetMapping("/id/{propertyId}")
    @ResponseStatus(HttpStatus.OK)
    fun getPropertyByPropertyId(@PathVariable propertyId: String): ResponseEntity<PropertyDto> {
        val property = this.propertyService.getPropertyByPropertyId(propertyId)
        return if (property.isEmpty)
            ResponseEntity.notFound().build()
        else
            ResponseEntity.ok(property.get())
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProperty(@RequestBody propertyForm: PropertyForm): ResponseEntity<PropertyDto> {
        val property = this.propertyService.createProperty(propertyForm)
        return if (property.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(property.get())
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateProperty(@RequestBody propertyDto: PropertyDto): ResponseEntity<PropertyDto> {
        val property = this.propertyService.updateProperty(propertyDto)
        return if (property.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(property.get())
    }

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
