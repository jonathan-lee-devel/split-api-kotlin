package io.jonathanlee.splitapi.service.auth.impl

import io.jonathanlee.splitapi.form.auth.UserRegistrationForm
import io.jonathanlee.splitapi.model.auth.User
import io.jonathanlee.splitapi.model.auth.VerificationToken
import io.jonathanlee.splitapi.repository.auth.UserRepository
import io.jonathanlee.splitapi.repository.auth.VerificationTokenRepository
import io.jonathanlee.splitapi.service.auth.RegistrationService
import io.jonathanlee.splitapi.validation.auth.EmailExistsException
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegistrationServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val verificationTokenRepository: VerificationTokenRepository,
    private val mailSender: JavaMailSender
) : RegistrationService {

    @Value("#{environment.MAIL_USERNAME}")
    private lateinit var mailUsername: String

    object RegistrationServiceConstants {
        const val TOKEN_EXPIRATION_TIME_MINUTES = 15
    }

    override fun registerNewUser(userRegistrationForm: UserRegistrationForm): User {
        if (!userRegistrationForm.validate())
            throw IllegalStateException("Invalid user registration form")

        val userCheck: User = this.userRepository.findByEmail(userRegistrationForm.email)
        if (userCheck != null)
            throw EmailExistsException("An account already exists for the username: ${userRegistrationForm.email}")

        val user = User(
            0L,
            userRegistrationForm.email,
            this.passwordEncoder.encode(userRegistrationForm.password),
            false,
            Date(),
            null,
            null
        )

        user.verificationToken = this.generateVerificationToken(user)

        this.sendVerificationEmail(user)
        return this.userRepository.save(user)
    }

    override fun confirmNewUser(token: String): User {
        val user = this.verificationTokenRepository.findByToken(token).user
        user.enabled = true
        this.userRepository.save(user)
        return user
    }

    private fun generateVerificationToken(user: User): VerificationToken {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = Date().time
        calendar.add(Calendar.MINUTE, RegistrationServiceConstants.TOKEN_EXPIRATION_TIME_MINUTES)
        val expiryDate = Date(calendar.time.time)

        return VerificationToken(
            0L,
            UUID.randomUUID().toString(),
            user,
            expiryDate
        )
    }

    private fun sendVerificationEmail(user: User) {
        val confirmationUrl = "http://localhost:8080/register/confirm?token=${user.verificationToken?.token}"

        val message = SimpleMailMessage()
        message.setTo(user.email)
        message.setSubject("Registration Confirmation")
        message.setText("Please open the following URL to verify your account: \r\n$confirmationUrl")
        message.setFrom(mailUsername)

        this.mailSender.send(message)
    }

}
