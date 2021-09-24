package io.jonathanlee.splitapi.service.auth.impl

import io.jonathanlee.splitapi.dto.auth.UserDto
import io.jonathanlee.splitapi.form.auth.PasswordResetForm
import io.jonathanlee.splitapi.model.auth.PasswordResetToken
import io.jonathanlee.splitapi.model.auth.User
import io.jonathanlee.splitapi.repository.auth.PasswordResetTokenRepository
import io.jonathanlee.splitapi.repository.auth.UserRepository
import io.jonathanlee.splitapi.service.auth.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

/**
 * Implementation of service used to manage user data.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordResetTokenRepository: PasswordResetTokenRepository,
    private val passwordEncoder: PasswordEncoder,
    private val mailSender: JavaMailSender
) : UserService {

    /**
     * Mail username obtained from environment variable.
     */
    @Value("#{environment.MAIL_USERNAME}")
    private lateinit var mailUsername: String

    /**
     * Object used to contain user service constant values.
     */
    object UserServiceConstants {
        const val TOKEN_EXPIRATION_TIME_MINUTES = 15
    }

    /**
     * Implementation of method used to obtain available users.
     *
     * @return collection of available users.
     */
    override fun getUsers(): Collection<UserDto> {
        return this.userRepository.findAll().map { UserDto(it) }
    }

    /**
     * Implementation of method used obtain a specific user by email.
     *
     * @param email of user specified.
     * @return specific user data obtained according to provided email.
     */
    override fun getUserByEmail(email: String): Optional<UserDto> {
        val user = this.userRepository.findByEmail(email)
        return if (user == null)
            Optional.empty()
        else
            Optional.of(UserDto(user))
    }

    /**
     * Implementation of method used to obtain a specific user by user ID.
     *
     * @param userId ID of user specified.
     * @return specific user data obtained according to provided user ID.
     */
    override fun getUserByUserId(userId: String): Optional<UserDto> {
        val user = this.userRepository.findByUserId(userId)
        return if (user == null)
            Optional.empty()
        else
            Optional.of(UserDto(user))
    }

    /**
     * Implementation of method used to reset password for user by email.
     *
     * @param email email of user for which password is to be reset
     * @return user data contained in a UserDto
     */
    @Transactional
    override fun resetPasswordByEmail(email: String): Optional<UserDto> {
        var user = this.userRepository.findByEmail(email)
        return if (user == null) {
            Optional.empty()
        } else {
            val existingToken = this.passwordResetTokenRepository.findByUserId(user.id)
            if (existingToken != null) {
                this.passwordResetTokenRepository.delete(existingToken)
                user.passwordResetToken = null
                this.userRepository.save(user)
            }
            val token = this.generatePasswordResetToken(user)
            this.passwordResetTokenRepository.save(token)
            user = this.userRepository.findByUserId(user.userId) // Updated user data with newly generated token
            user.passwordResetToken = token
            this.userRepository.save(user)
            this.sendVerificationEmail(user)
            Optional.of(UserDto(user))
        }
    }

    /**
     * Implementation of method used to confirm password reset for user.
     *
     * @param tokenContents contents of password reset token.
     * @param passwordResetForm password reset form data used to reset user password.
     * @return user data contained in a UserDto.
     */
    override fun confirmPasswordReset(tokenContents: String, passwordResetForm: PasswordResetForm): Optional<UserDto> {
        val token = this.passwordResetTokenRepository.findByToken(tokenContents)
        return if (token.expiryDate.after(Date())) {
            token.user.password = this.passwordEncoder.encode(passwordResetForm.password)
            this.userRepository.save(token.user)
            this.passwordResetTokenRepository.delete(token) // Delete the token after use
            Optional.of(UserDto(token.user))
        } else {
            Optional.empty()
        }
    }

    /**
     * Helper method used to generate password reset token used to confirm password reset request.
     *
     * @param user for which password reset token is to be generated.
     * @return password reset token generated and used to confirm password reset request.
     */
    private fun generatePasswordResetToken(user: User): PasswordResetToken {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = Date().time
        calendar.add(
            Calendar.MINUTE,
            UserServiceImpl.UserServiceConstants.TOKEN_EXPIRATION_TIME_MINUTES
        )
        val expiryDate = Date(calendar.time.time)

        return PasswordResetToken(
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
        val resetUrl = "http://localhost:8080/user/resetPassword/confirm?token=${user.passwordResetToken?.token}"

        val message = SimpleMailMessage()
        message.setTo(user.email)
        message.setSubject("Password Reset")
        message.setText("Please open the following URL to reset your password: \r\n$resetUrl")
        message.setFrom(mailUsername)

        this.mailSender.send(message)
    }

}
