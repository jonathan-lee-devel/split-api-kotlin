package io.jonathanlee.splitapi.service

import io.jonathanlee.splitapi.dto.RenterDto
import io.jonathanlee.splitapi.form.RenterForm
import java.util.*

interface RenterService {

    fun getRenters(): Collection<RenterDto>

    fun getRenterByRenterId(renterId: String): Optional<RenterDto>

    fun createRenter(renterForm: RenterForm): Optional<RenterDto>

    fun updateRenter(renterDto: RenterDto): Optional<RenterDto>

    fun deleteRenterByRenterId(renterId: String): Optional<RenterDto>

}
