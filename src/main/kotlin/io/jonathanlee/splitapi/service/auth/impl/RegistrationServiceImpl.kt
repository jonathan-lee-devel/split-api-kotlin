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

/**
 * Implementation of user registration service used to manage user registrations.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Service
class RegistrationServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val verificationTokenRepository: VerificationTokenRepository,
    private val mailSender: JavaMailSender
) : RegistrationService {

    /**
     * Mail username obtained from environment variable.
     */
    @Value("#{environment.MAIL_USERNAME}")
    private lateinit var mailUsername: String

    /**
     * Object used to contain registration service constant values.
     */
    object RegistrationServiceConstants {
        const val TOKEN_EXPIRATION_TIME_MINUTES = 15
    }

    /**
     * Implementation of method used to register new users according to user information passed via user registration form.
     *
     * @param userRegistrationForm user information passed via user registration form.
     * @return newly created user entity
     */
    override fun registerNewUser(userRegistrationForm: UserRegistrationForm): User {
        if (!userRegistrationForm.validate())
            throw IllegalStateException("Invalid user registration form")

        val userCheck: User = this.userRepository.findByEmail(userRegistrationForm.email)
        if (userCheck != null)
            throw EmailExistsException("An account already exists for the username: ${userRegistrationForm.email}")

        val user = User(
            0L,
            UUID.randomUUID().toString(),
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

    /**
     * Implementation of method used to confirm new users according to verification token contents.
     *
     * @param token contents of verification token used to confirm newly registered user.
     * @return newly confirmed registered user entity.
     */
    override fun confirmNewUser(token: String): User {
        val user = this.verificationTokenRepository.findByToken(token).user
        user.enabled = true
        this.userRepository.save(user)
        return user
    }

    /**
     * Helper method used to generate verification token used to confirm newly registered user.
     *
     * @param user for which verification token is to be generated.
     * @return verification token generated to be used to confirm newly registered user.
     */
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

    /**
     * Helper method used to send verification email to registered user email address.
     *
     * @param user which is to be emailed with verification URL.
     */
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
