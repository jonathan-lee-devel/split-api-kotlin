package io.jonathanlee.splitapi.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

/**
 * Basic configuration file for Java Mail Sender.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Configuration
class MailConfig {

    /**
     * Mail username obtained from environment variable.
     */
    @Value("#{environment.MAIL_USERNAME}")
    private lateinit var mailUsername: String

    /**
     * Mail password obtained from environment variable.
     */
    @Value("#{environment.MAIL_PASSWORD}")
    private lateinit var mailPassword: String


    /**
     * Primary Java Mail sender bean configured for gmail SMTP according to environment variables.
     */
    @Bean
    fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = "smtp.gmail.com"
        mailSender.port = 587

        mailSender.username = mailUsername
        mailSender.password = mailPassword

        val properties = mailSender.javaMailProperties
        properties["mail.transport.protocol"] = "smtp"
        properties["mail.smtp.auth"] = "true"
        properties["mail.smtp.starttls.enable"] = "true"
        properties["mail.debug"] = "false"

        return mailSender
    }

}
