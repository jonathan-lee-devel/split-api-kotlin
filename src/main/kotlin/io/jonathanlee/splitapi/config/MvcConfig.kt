package io.jonathanlee.splitapi.config

import io.jonathanlee.splitapi.interceptor.auth.AuthorizationInterceptor
import io.jonathanlee.splitapi.interceptor.auth.ExpenseAuthorizationInterceptor
import io.jonathanlee.splitapi.interceptor.auth.PropertyAuthorizationInterceptor
import io.jonathanlee.splitapi.interceptor.auth.RenterAuthorizationInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MvcConfig(
    private val authorizationInterceptor: AuthorizationInterceptor,
    private val expenseAuthorizationInterceptor: ExpenseAuthorizationInterceptor,
    private val propertyAuthorizationInterceptor: PropertyAuthorizationInterceptor,
    private val renterAuthorizationInterceptor: RenterAuthorizationInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authorizationInterceptor).excludePathPatterns("/register/**")
        registry.addInterceptor(expenseAuthorizationInterceptor).addPathPatterns("/expense/**")
        registry.addInterceptor(propertyAuthorizationInterceptor).addPathPatterns("/property/**")
        registry.addInterceptor(renterAuthorizationInterceptor).addPathPatterns("/renter/**")
    }

}
