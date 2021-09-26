package io.jonathanlee.splitapi.controller

import io.jonathanlee.splitapi.dto.RenterDto
import io.jonathanlee.splitapi.form.RenterForm
import io.jonathanlee.splitapi.service.RenterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/renter")
class RenterController(
    private val renterService: RenterService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getRenters(): ResponseEntity<Collection<RenterDto>> {
        return ResponseEntity.ok(this.renterService.getRenters())
    }

    @GetMapping("/id/{renterId}")
    @ResponseStatus(HttpStatus.OK)
    fun getRenterByRenterId(@PathVariable renterId: String): ResponseEntity<RenterDto> {
        val renter = this.renterService.getRenterByRenterId(renterId)
        return if (renter.isEmpty)
            ResponseEntity.notFound().build()
        else
            ResponseEntity.ok(renter.get())
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRenter(@RequestBody renterForm: RenterForm): ResponseEntity<RenterDto> {
        val renter = this.renterService.createRenter(renterForm)
        return if (renter.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(renter.get())
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateRenter(@RequestBody renterDto: RenterDto): ResponseEntity<RenterDto> {
        val renter = this.renterService.updateRenter(renterDto)
        return if (renter.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(renter.get())
    }

    @DeleteMapping("/id/{renterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRenterByRenterId(@PathVariable renterId: String): ResponseEntity<RenterDto> {
        val renter = this.renterService.deleteRenterByRenterId(renterId)
        return if (renter.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(renter.get())
    }

}
