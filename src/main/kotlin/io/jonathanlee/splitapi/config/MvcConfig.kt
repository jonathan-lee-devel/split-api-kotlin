package io.jonathanlee.splitapi.config

import io.jonathanlee.splitapi.interceptor.auth.AuthorizationInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MvcConfig(private val authorizationInterceptor: AuthorizationInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authorizationInterceptor)
    }

}
