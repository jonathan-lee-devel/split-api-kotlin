package io.jonathanlee.splitapi.controller

import io.jonathanlee.splitapi.dto.RenterDto
import io.jonathanlee.splitapi.form.RenterForm
import io.jonathanlee.splitapi.service.RenterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * REST controller used for renter interactions.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@RestController
@RequestMapping("/renter")
class RenterController(
    private val renterService: RenterService
) {

    /**
     * GET request used to obtain all available renters.
     *
     * @return Collection of renter information via RenterDto contained in a ResponseEntity.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getRenters(): ResponseEntity<Collection<RenterDto>> {
        return ResponseEntity.ok(this.renterService.getRenters())
    }

    /**
     * GET request used to obtain specified renter.
     *
     * @param renterId renter ID of renter information to be obtained.
     * @return renter information of specified renter via RenterDto contained in a ResponseEntity.
     */
    @GetMapping("/id/{renterId}")
    @ResponseStatus(HttpStatus.OK)
    fun getRenterByRenterId(@PathVariable renterId: String): ResponseEntity<RenterDto> {
        val renter = this.renterService.getRenterByRenterId(renterId)
        return if (renter.isEmpty)
            ResponseEntity.notFound().build()
        else
            ResponseEntity.ok(renter.get())
    }

    /**
     * POST request used to create a new renter.
     *
     * @param renterForm form data used to create new renter.
     * @return renter data contained in a RenterDto.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRenter(
        username: String,
        @RequestBody renterForm: RenterForm
    ): ResponseEntity<RenterDto> {
        val renter = this.renterService.createRenter(username, renterForm)
        return if (renter.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(renter.get())
    }

    /**
     * PUT request used to update a renter.
     *
     * @param renterDto DTO containing updated renter information.
     * @return renter data contained in a RenterDto.
     */
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateRenter(@RequestBody renterDto: RenterDto): ResponseEntity<RenterDto> {
        val renter = this.renterService.updateRenter(renterDto)
        return if (renter.isEmpty)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(renter.get())
    }

    /**
     * DELETE request used to delete a renter.
     *
     * @param renterId ID of renter to be deleted.
     * @return renter data contained in a RenterDto.
     */
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
