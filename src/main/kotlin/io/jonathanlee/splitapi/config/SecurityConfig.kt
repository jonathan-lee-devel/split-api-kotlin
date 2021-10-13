package io.jonathanlee.splitapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * Basic configuration file for Application Security.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    /**
     * Primary configuration method for application security.
     */
    override fun configure(http: HttpSecurity?) {
        http
            ?.authorizeRequests()
            ?.antMatchers("/register/**")?.permitAll()
            ?.antMatchers("/login/**")?.permitAll()
            ?.antMatchers("/user/**")?.permitAll()
            ?.antMatchers("/v3/api-docs/**")?.permitAll()
            ?.antMatchers("/swagger-ui/**")?.permitAll()
            ?.anyRequest()?.authenticated()
            ?.and()
            ?.cors()?.and()
            ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ?.and()
            ?.csrf()?.disable()
            ?.oauth2ResourceServer()?.jwt()
    }

    /**
     * Password encoder bean configured as BCrypt password encoder.
     *
     * @return BCrypt password encoder.
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}
