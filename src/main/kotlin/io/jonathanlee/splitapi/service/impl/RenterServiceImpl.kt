package io.jonathanlee.splitapi.service.impl

import io.jonathanlee.splitapi.dto.RenterDto
import io.jonathanlee.splitapi.form.RenterForm
import io.jonathanlee.splitapi.model.Renter
import io.jonathanlee.splitapi.repository.RenterRepository
import io.jonathanlee.splitapi.repository.auth.UserRepository
import io.jonathanlee.splitapi.service.RenterService
import org.springframework.stereotype.Service
import java.util.*

@Service
class RenterServiceImpl(
    private val renterRepository: RenterRepository,
    private val userRepository: UserRepository
) : RenterService {

    override fun getRenters(): Collection<RenterDto> {
        return this.renterRepository.findAll().map { RenterDto(it) }
    }

    override fun getRenterByRenterId(renterId: String): Optional<RenterDto> {
        val renter = this.renterRepository.findByRenterId(renterId)
        return if (renter == null)
            Optional.empty()
        else
            Optional.of(RenterDto(renter))
    }

    override fun createRenter(renterForm: RenterForm): Optional<RenterDto> {
        val user = this.userRepository.findByUserId(renterForm.userId)
        if (user == null)
            return Optional.empty()

        val renter = Renter(
            0L,
            UUID.randomUUID().toString(),
            Date(),
            renterForm.name,
            user
        )
        this.renterRepository.save(renter)

        return Optional.of(RenterDto(renter))
    }

    override fun updateRenter(renterDto: RenterDto): Optional<RenterDto> {
        val renter = this.renterRepository.findByRenterId(renterDto.renterId)
        if (renter == null)
            return Optional.empty()

        renter.name = renterDto.name

        return Optional.of(RenterDto(this.renterRepository.save(renter)))
    }

    override fun deleteRenterByRenterId(renterId: String): Optional<RenterDto> {
        val renter = this.renterRepository.findByRenterId(renterId)
        if (renter == null)
            return Optional.empty()

        this.renterRepository.delete(renter)

        return Optional.of(RenterDto(renter))
    }

}
