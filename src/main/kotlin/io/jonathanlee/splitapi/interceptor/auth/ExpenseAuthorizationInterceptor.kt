package io.jonathanlee.splitapi.interceptor.auth

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ExpenseAuthorizationInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val principal = SecurityContextHolder.getContext().authentication.principal
        if (principal != null && principal !is String) {
            val jwt = principal as Jwt
        }

        val roles = request.getAttribute(AuthorizationInterceptor.rolesAttribute) as Collection<*>
        return roles.contains("USER") || roles.contains("ADMIN")
    }

}
