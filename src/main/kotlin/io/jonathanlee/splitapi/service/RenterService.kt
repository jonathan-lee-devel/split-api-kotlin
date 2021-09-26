package io.jonathanlee.splitapi.service

import io.jonathanlee.splitapi.dto.RenterDto
import io.jonathanlee.splitapi.form.RenterForm
import java.util.*

/**
 * Renter service used to manage renters.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface RenterService {

    /**
     * Method used to obtain available renters.
     *
     * @return renter data contained in RenterDto.
     */
    fun getRenters(): Collection<RenterDto>

    /**
     * Method used to obtain a specific renter by renter ID.
     *
     * @param renterId ID of specific renter to be obtained.
     * @return renter data contained in a RenterDto.
     */
    fun getRenterByRenterId(renterId: String): Optional<RenterDto>

    /**
     * Method used to create a renter.
     *
     * @param renterForm form data used to create a renter.
     * @return renter data contained in a RenterDto.
     */
    fun createRenter(renterForm: RenterForm): Optional<RenterDto>

    /**
     * Method used to update a renter.
     *
     * @param renterDto renter data for updated renter.
     * @return renter data contained in a RenterDto.
     */
    fun updateRenter(renterDto: RenterDto): Optional<RenterDto>

    /**
     * Method used to delete a property.
     *
     * @param renterId ID of renter to be deleted.
     * @return renter data contained in a RenterDto.
     */
    fun deleteRenterByRenterId(renterId: String): Optional<RenterDto>

}
